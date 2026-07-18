package com.example.fitness.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.fitness.dto.ProgressResponse;
import com.example.fitness.service.ProgressService;

@RestController
@RequestMapping("/logs/progress")
//@CrossOrigin(origins = "${app.cors.allowed-origins:http://localhost:3000}")
@CrossOrigin(origins = "*")
public class ProgressController {

	@Autowired
	private ProgressService progressService;

	@GetMapping("/{userId}")
	public ResponseEntity<?> getProgress(@PathVariable Long userId) {
		ProgressResponse progressResponse = progressService.analyzeProgress(userId);

		if (progressResponse == null) {
			return ResponseEntity.badRequest().body("No Logs Found");
		}
		return ResponseEntity.ok(progressResponse);

	}

}
