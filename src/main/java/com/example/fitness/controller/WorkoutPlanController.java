package com.example.fitness.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.fitness.entity.WorkoutPlan;
import com.example.fitness.service.WorkoutPlanService;

@RestController
@RequestMapping("/api/workout")
public class WorkoutPlanController {

	@Autowired
	private WorkoutPlanService workoutPlanService;

	@GetMapping("/user/{userId}")
	public List<WorkoutPlan> getWorkoutByUserId(@PathVariable Long userId) {
		List<WorkoutPlan> plans = workoutPlanService.getWorkoutByUserId(userId);

		if (plans.isEmpty()) {
			throw new RuntimeException("No workout plan found");
		}

		return plans;
	}
	
	// Get all exercises (for Exercise Library)
	@GetMapping("/all")
	public ResponseEntity<?> getAllExercises() {
	    try {
	        List<WorkoutPlan> allExercises = workoutPlanService.getAllExercises();
	        return ResponseEntity.ok(allExercises);
	    } catch (Exception e) {
	        return ResponseEntity.badRequest().body("Error: " + e.getMessage());
	    }
	}

	
}
