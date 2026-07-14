package com.example.fitness.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.fitness.dto.RecommendationResponse;
import com.example.fitness.service.RecommendationService;

@RestController
@RequestMapping("/recommendations")
@CrossOrigin(origins = "${app.cors.allowed-origins:http://localhost:3000}")
public class RecommendationController {

//	private final UserController userController;

	@Autowired
	private RecommendationService recommendationService;

//	RecommendationController(UserController userController) {
//		this.userController = userController;
//	}

	@GetMapping("/{userId}")
	public ResponseEntity<?> getRecommendation(@PathVariable Long userId) {
		RecommendationResponse response = recommendationService.getRecommendation(userId);

		if (response == null) {
			return ResponseEntity.badRequest().body("user not found");
		}

		return ResponseEntity.ok(response);
	}

}
