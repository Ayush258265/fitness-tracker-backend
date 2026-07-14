package com.example.fitness.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.fitness.dto.CalorieDataDTO;
import com.example.fitness.dto.ExerciseDTO;
import com.example.fitness.dto.MealDTO;
import com.example.fitness.repository.CalorieRepository;
import com.example.fitness.entity.CalorieLog;

@Service
public class CalorieService {

	@Autowired
	private CalorieRepository calorieRepository;

	private static final int DEFAULT_CALORIE_GOAL = 2000;

	public CalorieDataDTO getTodayCalories(Long userId) {
		
		LocalDate today = LocalDate.now();
		return getCaloriesByDate(userId, today);
		
	}

	private CalorieDataDTO getCaloriesByDate(Long userId, LocalDate date) {

		List<CalorieLog> logs = calorieRepository.findByUserIdAndLogDateOrderByCreatedAtDesc(userId, date);

		Integer consumed = calorieRepository.getTotalConsumedByDate(userId, date);
		Integer burned = calorieRepository.getTotalBurnedByDate(userId, date);

		if (consumed == null)
			consumed = 0;
		if (burned == null)
			burned = 0;

		List<MealDTO> meals = new ArrayList<>();
		List<ExerciseDTO> exercises = new ArrayList<>();

		for (CalorieLog log : logs) {
			if ("CONSUMED".equals(log.getType())) {
				MealDTO meal = new MealDTO(log.getId(), log.getCalories(), log.getDescription(),
						log.getCreatedAt().format(DateTimeFormatter.ofPattern("hh:mm a")));
				meals.add(meal);
			} else if ("BURNED".equals(log.getType())) {
				ExerciseDTO exercise = new ExerciseDTO(log.getId(), log.getCalories(), log.getDescription(),
						log.getCreatedAt().format(DateTimeFormatter.ofPattern("hh:mm a")));
				exercises.add(exercise);
			}
		}

		return new CalorieDataDTO(consumed, burned, DEFAULT_CALORIE_GOAL, date.toString(), meals, exercises);
	}

	// Add consumed calories (meal) for today
	public CalorieLog addConsumedCalories(Long userId, Integer calories, String mealName) {
		CalorieLog log = new CalorieLog(userId, LocalDate.now(), calories, "CONSUMED", mealName, LocalDateTime.now());
		return calorieRepository.save(log);
	}

	// Add burned calories (exercise) for today
	public CalorieLog addBurnedCalories(Long userId, Integer calories, String exerciseName) {
		CalorieLog log = new CalorieLog(userId, LocalDate.now(), calories, "BURNED", exerciseName, LocalDateTime.now());
		return calorieRepository.save(log);
	}
}
