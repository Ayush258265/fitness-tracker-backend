package com.example.fitness.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.fitness.dto.SaveWorkoutRequestDTO;
import com.example.fitness.entity.WorkoutHistory;
import com.example.fitness.service.WorkoutHistoryService;

@RestController
@RequestMapping("/workout/history")
//@CrossOrigin(origins = "${app.cors.allowed-origins:http://localhost:3000}")
//@CrossOrigin(origins = "*")
public class WorkoutHistoryController {

	@Autowired
	private WorkoutHistoryService workoutHistoryService;

	// Save completed workout
	@PostMapping("/save")
	public ResponseEntity<?> saveWorkout(@RequestBody Map<String, Object> request) {
		try {
			Long userId = Long.valueOf(request.get("userId").toString());
			String workoutName = (String) request.get("workoutName");
			Integer duration = (Integer) request.get("duration");
			Integer exercisesCompleted = (Integer) request.get("exercisesCompleted");
			Integer totalCalories = (Integer) request.get("totalCalories");

			WorkoutHistory history = workoutHistoryService.saveWorkout(userId, workoutName, duration,
					exercisesCompleted, totalCalories);
			return ResponseEntity.ok(history);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body("Error: " + e.getMessage());
		}
	}

	// Get all workout history for a user
	@GetMapping("/{userId}")
	public ResponseEntity<?> getWorkoutHistory(@PathVariable Long userId) {
		try {
			List<WorkoutHistory> history = workoutHistoryService.getUserWorkoutHistory(userId);
			return ResponseEntity.ok(history);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body("Error: " + e.getMessage());
		}
	}

	// Get workout statistics (streak, total calories, weekly activity)
	@GetMapping("/stats/{userId}")
	public ResponseEntity<?> getWorkoutStats(@PathVariable Long userId) {
		try {
			Map<String, Object> stats = workoutHistoryService.getWorkoutStats(userId);
			return ResponseEntity.ok(stats);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body("Error: " + e.getMessage());
		}
	}

	// Get weekly workout summary for charts
	@GetMapping("/weekly-summary/{userId}")
	public ResponseEntity<?> getWeeklySummary(@PathVariable Long userId) {
		try {
			Map<String, Object> summary = workoutHistoryService.getWeeklySummary(userId);

			return ResponseEntity.ok(summary);

		} catch (Exception e) {
			return ResponseEntity.badRequest().body("Error: " + e.getMessage());
		}
	}

}
