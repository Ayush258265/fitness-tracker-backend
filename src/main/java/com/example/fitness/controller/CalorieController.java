package com.example.fitness.controller;

import java.util.HashMap;
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

import com.example.fitness.dto.AddBurnedRequestDTO;
import com.example.fitness.dto.AddConsumedRequestDTO;
import com.example.fitness.dto.CalorieDataDTO;
import com.example.fitness.entity.CalorieLog;
import com.example.fitness.service.CalorieService;

@RestController
@RequestMapping("/calorie")
//@CrossOrigin(origins = "${app.cors.allowed-origins:http://localhost:3000}")
//@CrossOrigin(origins = "*")
public class CalorieController {

	@Autowired
	private CalorieService caloriesService;

	// Get today's calorie data
	@GetMapping("/today/{userId}")
	public ResponseEntity<?> getTodayCalories(@PathVariable Long userId) {

		try {
			CalorieDataDTO data = caloriesService.getTodayCalories(userId);
			return ResponseEntity.ok(data);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body("Error: " + e.getMessage());
		}

	}

	@PostMapping("/add/consumed/{userId}")
	public ResponseEntity<?> addConsumedCalories(@PathVariable Long userId,
			@RequestBody AddConsumedRequestDTO request) {

		try {
			CalorieLog log = caloriesService.addConsumedCalories(userId, request.getConsumed(),
					request.getMeal().getMeal());

			Map<String, Object> response = new HashMap<>();

			response.put("message", "Calories added successfully");
			response.put("log", log);

			return ResponseEntity.ok(response);

		} catch (Exception e) {
			return ResponseEntity.badRequest().body("Error: " + e.getMessage());
		}

	}

	@PostMapping("/add/burned/{userId}")
	public ResponseEntity<?> addBurnedCalories(@PathVariable Long userId, @RequestBody AddBurnedRequestDTO request) {

		try {
			CalorieLog log = caloriesService.addBurnedCalories(userId, request.getBurned(),
					request.getExercise().getExercise());

			Map<String, Object> response = new HashMap<>();

			response.put("message", "Exercise calories added successfully");
			response.put("log", log);

			return ResponseEntity.ok(response);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body("Error: " + e.getMessage());
		}
	}

}
