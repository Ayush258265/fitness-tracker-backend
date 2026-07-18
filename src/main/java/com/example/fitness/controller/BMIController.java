package com.example.fitness.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.fitness.dto.BMIResponse;
import com.example.fitness.service.BMIResponseService;

@RestController
@RequestMapping("/users/bmi")
//@CrossOrigin(origins = "${app.cors.allowed-origins:http://localhost:3000}")
@CrossOrigin(origins = "*")
public class BMIController {

	@Autowired
	private BMIResponseService bmiResponseService ;
	
	@GetMapping("/{userId}")
	public ResponseEntity<?> getBMI(@PathVariable Long userId){
		BMIResponse bmiResponse = bmiResponseService.calculateBMI(userId) ;
		
		if(bmiResponse == null) {
			return ResponseEntity.status(404).body("User not found");
		}
		return ResponseEntity.ok(bmiResponse) ;
	}
	
}
