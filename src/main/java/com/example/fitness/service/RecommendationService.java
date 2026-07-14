package com.example.fitness.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.fitness.dto.RecommendationResponse;
import com.example.fitness.entity.User;
import com.example.fitness.repository.UserRepository;
//import com.example.fitness.entity ;

@Service
public class RecommendationService {

	@Autowired
	private UserRepository userRepository;

	public RecommendationResponse getRecommendation(Long userId) {

		User user = userRepository.findById(userId).orElse(null);

		if (user == null) {
			return null;
		}

		String exercise = "";
		String diet = "";
		String advice = "";
		String intensity = "";

		int age = user.getAge();
		String goal = user.getGoal().toLowerCase();
		String dietType = user.getDietType().toLowerCase();

		if (age >= 5 && age <= 12) {
			exercise = "Light yoga, stretching, jumping, outdoor games";
			intensity = "Light";
			advice = "Focus on growth, flexibility and fun physical activity";
		} else if (age >= 13 && age <= 19) {
			exercise = "Running, sports, bodyweight exercises, cardio";
			intensity = "Moderate";
			advice = "Build stamina, strength and healthy routine";
		} else if (age >= 20 && age <= 40) {
			exercise = "Gym workout, strength training, cardio, cycling";
			intensity = "High";
			advice = "Maintain strength, muscle and fitness balance";
		} else if (age >= 41 && age <= 60) {
			exercise = "Walking, light gym, yoga, stretching";
			intensity = "Moderate";
			advice = "Focus on joint care, heart health and consistency";
		} else {
			exercise = "Walking, chair exercise, breathing exercise, light stretching";
			intensity = "Light";
			advice = "Focus on mobility, balance and safe movement";
		}

		if (goal.contains("loss")) {
			exercise += ", fat-burning workout";
			advice += ". Calorie deficit and regular exercise will help";
		} else if (goal.contains("gain")) {
			exercise += ", muscle-building workout";
			advice += ". Take enough protein and strength training";
		} else {
//			exercise += ", Do basice exercise"; 
			advice += ". Keep a balanced routine for maintenance";
		}

		if (dietType.equals("veg")) {
			diet = "Paneer, milk, curd, oats, fruits, green vegetables, dal, nuts";
		} else if (dietType.equals("non-veg")) {
			diet = "Eggs, chicken, fish, milk, rice, vegetables, fruits";
		} else {
			diet = "Balanced mix of veg and non-veg foods with protein, fiber and vitamins";
		}

		return new RecommendationResponse(exercise, diet, advice, intensity);

	}

}
