package com.example.fitness.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.fitness.dto.BMIResponse;
import com.example.fitness.dto.ProgressResponse;
import com.example.fitness.dto.SmartRecommendationResponse;
import com.example.fitness.entity.User;
import com.example.fitness.repository.UserRepository;

@Service
public class SmartRecommendationService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private BMIResponseService bmiService;

	@Autowired
	private ProgressService progressService;

	public SmartRecommendationResponse getRecommendation(Long userId) {

		User user = userRepository.findById(userId).orElse(null);

		if (user == null)
			return null;

		BMIResponse bmiResponse = bmiService.calculateBMI(userId);

		double bmi = bmiResponse.getBmi();
		String bmiCategory = bmiResponse.getCategory();

		ProgressResponse progress = progressService.analyzeProgress(userId);

		String trend = progress.getWeightTrend();

		String status;
		String finalAdvice;

		if (bmiCategory.equals("OverWeight") || bmiCategory.equals("Obese")) {
			status = "Weight Loss Mode";

			if (trend.equals("Decreasing")) {
				finalAdvice = "Great! You are losing weight. Maintain calorie deficit.";
			} else {
				finalAdvice = "Start calorie deficit and increase cardio.";
			}
		} else if (bmiCategory.equals("UnderWeight")) {
			status = "Weight Gain Mode";

			if (trend.equals("Increasing")) {
				finalAdvice = "Good progress! Continue high-calorie diet.";
			} else {
				finalAdvice = "Increase calorie intake and strength training.";
			}
		} else {

			status = "Maintenance Mode";
			finalAdvice = "Maintain balanced diet and regular exercise.";
		}

		return new SmartRecommendationResponse(status, bmi, progress, finalAdvice);
	}

}
