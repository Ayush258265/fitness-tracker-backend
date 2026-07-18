package com.example.fitness.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.fitness.dto.SmartRecommendationResponse;
import com.example.fitness.service.SmartRecommendationService;

@RestController
@RequestMapping("/api/recommendation")
//@CrossOrigin(origins = "${app.cors.allowed-origins:http://localhost:3000}")
@CrossOrigin(origins = "*")
public class SmartRecommendationController {

	@Autowired
	private SmartRecommendationService smartRecommendationService;

	@GetMapping("/{userId}")
	public SmartRecommendationResponse getRecommendation(@PathVariable Long userId) {
		return smartRecommendationService.getRecommendation(userId);
	}

//	@GetMapping("/simple/{userId}")
//	public String getSimpleRecommendation(@PathVariable Long userId) {
//		SmartRecommendationResponse response = getRecommendation(userId);
//		return response.getFinalAdvice(); // Just return the advice text
//	}

}