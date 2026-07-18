package com.example.fitness.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.fitness.entity.DailyLog;
import com.example.fitness.service.DailyLogService;

@RestController
@RequestMapping("/logs")
//@CrossOrigin(origins = "${app.cors.allowed-origins:http://localhost:3000}")
@CrossOrigin(origins = "*")
public class DailyLogController {

	@Autowired
	private DailyLogService logService ;
	
	@PostMapping("add/{UserId}")
	public ResponseEntity<?> addLog(@PathVariable Long UserId ,@RequestBody DailyLog log){
		DailyLog savedLog = logService.addLog(UserId, log);
		
		if(savedLog == null) {
			return ResponseEntity.badRequest().body("User not found") ;
		}
		return ResponseEntity.ok(savedLog) ;
		
	}
	
	@GetMapping("/user/{userId}")
	public ResponseEntity<List<DailyLog>> getUserLog(@PathVariable long userId){
		return ResponseEntity.ok(logService.getUserLogs(userId)) ;
	}
	
	
}
