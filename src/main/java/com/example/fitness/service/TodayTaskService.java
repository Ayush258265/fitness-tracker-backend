package com.example.fitness.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.fitness.dto.Calorie;
import com.example.fitness.dto.Diet;
import com.example.fitness.dto.TodayTask;
import com.example.fitness.dto.Water;
import com.example.fitness.dto.WorkoutPlanDTO;
import com.example.fitness.entity.WorkoutPlan;
import com.example.fitness.repository.WorkoutPlanRepository;

import java.util.List;
import java.util.ArrayList;
//import java.util.stream.Collectors;

@Service
public class TodayTaskService {

    @Autowired
    private BMIResponseService bmiResponseService;
    
    @Autowired
    private WorkoutPlanRepository workoutPlanRepository;

    public TodayTask getUserTodayTask(Long userId) {

        String category = bmiResponseService.calculateBMI(userId).getCategory();

        TodayTask todayTask = new TodayTask();

        // ✅ NEW: Return structured workout plan (not text)
        List<WorkoutPlanDTO> workoutPlan = getWorkoutPlan(category);
        todayTask.setWorkoutPlan(workoutPlan);  // Add this field to TodayTask DTO

        // Diet recommendation based on BMI category
        Diet diet = new Diet();
        if (category.equals("UNDERWEIGHT")) {
            diet.setBreakfast("🍌 Banana shake + peanut butter toast");
            diet.setLunch("🍚 Rice + chicken/paneer + salad");
            diet.setDinner("🌯 Roti + dal + vegetables");
        } 
        else if (category.equals("NORMAL")) {
            diet.setBreakfast("🥣 Oats + milk + fruits");
            diet.setLunch("🍚 Rice + dal + salad");
            diet.setDinner("🌯 Roti + paneer + vegetables");
        } 
        else if (category.equals("OVERWEIGHT")) {
            diet.setBreakfast("🥚 Boiled eggs / sprouts");
            diet.setLunch("🥗 Grilled chicken + salad");
            diet.setDinner("🥣 Soup + grilled vegetables");
        }
        else {
            diet.setBreakfast("🥗 Fresh fruits + green tea");
            diet.setLunch("🥣 Light soup + salad");
            diet.setDinner("🥗 Steamed vegetables + dal");
        }
        todayTask.setDiet(diet);

        // Water recommendation
        Water water = new Water();
        if (category.equals("OBESE") || category.equals("OVERWEIGHT")) {
            water.setTarget(3.5);
            water.setCompleted(1.5);
        } else if (category.equals("UNDERWEIGHT")) {
            water.setTarget(2.5);
            water.setCompleted(1.0);
        } else {
            water.setTarget(3.0);
            water.setCompleted(1.2);
        }
        todayTask.setWater(water);

        // Calorie recommendation
        Calorie calories = new Calorie();
        if (category.equals("UNDERWEIGHT")) {
            calories.setTarget(2800);
            calories.setConsumed(1200);
        } else if (category.equals("NORMAL")) {
            calories.setTarget(2200);
            calories.setConsumed(1000);
        } else if (category.equals("OVERWEIGHT")) {
            calories.setTarget(1800);
            calories.setConsumed(800);
        } else {
            calories.setTarget(1500);
            calories.setConsumed(700);
        }
        todayTask.setCalorie(calories);

        return todayTask;
    }
    
    private List<WorkoutPlanDTO> getWorkoutPlan(String category) {
        List<WorkoutPlan> exercises = workoutPlanRepository.findByBmiCategoryOrderByPhase(category);
        
        List<WorkoutPlanDTO> workoutPlan = new ArrayList<>();
        
        for (WorkoutPlan ex : exercises) {
            WorkoutPlanDTO dto = new WorkoutPlanDTO();
            dto.setId(ex.getId());
            dto.setExerciseName(ex.getExerciseName());
            dto.setPhase(ex.getPhase());
            dto.setDuration(ex.getDuration());
            dto.setRestTime(ex.getRestTime());
            dto.setCompleted(false);  // For tracking
            workoutPlan.add(dto);
        }
        
        return workoutPlan;
    }
}