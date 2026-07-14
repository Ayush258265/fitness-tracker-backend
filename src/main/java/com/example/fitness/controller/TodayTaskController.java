package com.example.fitness.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.example.fitness.service.TodayTaskService;

@RestController
@RequestMapping("/todayTask")
public class TodayTaskController {

	@Autowired
	private TodayTaskService todayTaskService;

	@GetMapping("/{userId}")
	public ResponseEntity<?> getTodayTask(@PathVariable Long userId) {
		try {
			return ResponseEntity.ok(todayTaskService.getUserTodayTask(userId));
		} catch (Exception e) {
			return ResponseEntity.status(404).body("User not found or invalid ID");
		}
	}

}
