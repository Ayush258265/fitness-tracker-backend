package com.example.fitness.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.fitness.entity.WorkoutPlan;
import com.example.fitness.repository.WorkoutPlanRepository;

@Service
public class WorkoutPlanService {

	@Autowired
	private WorkoutPlanRepository workoutPlanRepository;

	@Autowired
	private BMIResponseService bmiResponseService;

	public List<WorkoutPlan> getWorkoutByUserId(Long userId) {

		var bmiResponse = bmiResponseService.calculateBMI(userId);

		if (bmiResponse == null) {
			throw new RuntimeException("User not found");
		}

		String category = bmiResponse.getCategory();

		return workoutPlanRepository.findByBmiCategoryOrderByPhase(category);
	}

	public List<WorkoutPlan> getAllExercises() {
		return workoutPlanRepository.findAll();
	}
	

}
