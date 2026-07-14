package com.example.fitness.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.fitness.dto.BMIResponse;
import com.example.fitness.entity.User;
import com.example.fitness.repository.UserRepository;

@Service
public class BMIResponseService {

	@Autowired
	private UserRepository userRepository ;
	
	public BMIResponse calculateBMI(Long userId) {
		User user = userRepository.findById(userId).orElse(null);
		
		if(user == null ) {
			return null ;
		}
		
		double heightInMetres = user.getHeight()/100.0 ;
		double weight = user.getWeight();
		
		if(user.getHeight() <= 0 || user.getWeight() <= 0) {
		    throw new RuntimeException("Invalid height or weight");
		}
		
		double bmi = weight / (heightInMetres * heightInMetres);
		
		String category ;
		
		if(bmi < 18.5) {
			category = "UNDERWEIGHT" ;
		}
		else if (bmi < 25) {
			category = "NORMAL" ;
		}
		else if(bmi < 30) {
			category = "OVERWEIGHT" ;
		}else {
			category = "OBESE" ;
		}
		
		return new BMIResponse(bmi, category);
	}
	
}
