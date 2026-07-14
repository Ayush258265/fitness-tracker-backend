package com.example.fitness.service;

import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.fitness.dto.ProgressResponse;
import com.example.fitness.entity.DailyLog;
import com.example.fitness.repository.DailyLogRepository;

@Service
public class ProgressService {

	@Autowired
	private DailyLogRepository logRepository;

	
	public ProgressResponse analyzeProgress(Long userId) {

	    List<DailyLog> logs = logRepository.findByUserId(userId);

	    if (logs == null || logs.isEmpty()) {
	        return new ProgressResponse(
	                "No Data",
	                0,
	                0,
	                "Unknown",
	                "Please add logs to track progress"
	        );
	    }

	    // sort logs
	    logs.sort(Comparator.comparing(DailyLog::getDate));

	    double totalConsumed = 0;
	    double totalBurned = 0;

	    for (DailyLog log : logs) {
	        totalConsumed += log.getCaloriesConsumed();
	        totalBurned += log.getCaloriesBurned();
	    }

	    double avgConsumed = totalConsumed / logs.size();
	    double avgBurned = totalBurned / logs.size();

	    String weightTrend;
	    String calorieBalance;
	    String suggestion;

	    // 🔥 CASE 1: Only ONE log
	    if (logs.size() == 1) {

	        weightTrend = "Not enough data";

	        if (avgConsumed < avgBurned) {
	            calorieBalance = "Deficit";
	            suggestion = "Good start for weight loss";
	        } else if (avgConsumed > avgBurned) {
	            calorieBalance = "Surplus";
	            suggestion = "Good start for muscle gain";
	        } else {
	            calorieBalance = "Balanced";
	            suggestion = "Maintain consistency";
	        }

	        return new ProgressResponse(
	                weightTrend,
	                avgConsumed,
	                avgBurned,
	                calorieBalance,
	                suggestion
	        );
	    }

	    // 🔥 CASE 2: MULTIPLE logs
	    double firstWeight = logs.get(0).getWeight();
	    double lastWeight = logs.get(logs.size() - 1).getWeight();

	    if (lastWeight < firstWeight) {
	        weightTrend = "Decreasing";
	    } else if (lastWeight > firstWeight) {
	        weightTrend = "Increasing";
	    } else {
	        weightTrend = "Stable";
	    }

	    if (avgConsumed < avgBurned) {
	        calorieBalance = "Deficit";
	        suggestion = "Good for weight loss";
	    } else if (avgConsumed > avgBurned) {
	        calorieBalance = "Surplus";
	        suggestion = "Good for muscle gain";
	    } else {
	        calorieBalance = "Balanced";
	        suggestion = "Maintain current lifestyle";
	    }

	    return new ProgressResponse(
	            weightTrend,
	            avgConsumed,
	            avgBurned,
	            calorieBalance,
	            suggestion
	    );
	}

}
