package com.example.fitness.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.fitness.entity.User;
import com.example.fitness.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository ;
	
	//Register new user
	public User registerUser(User user) {
		return userRepository.save(user) ;
	}
	
	// Authenticate user
	public User authenticate(String email , String password) {
		User user = userRepository.findByEmail(email);
		if(user != null && user.getPassword().equals(password)) {
			return user ;
		}
		return null ;
	}
	
	// Check if email exists
	public boolean existsByEmail(String email) {
		return userRepository.existsByEmail(email) ;
	}
	
	public User createUser(User user) {
		
		return userRepository.save(user) ;
	}
	
	public List<User> getAllUsers(User user){
		return userRepository.findAll() ;
	}
	
	public User getUserById(Long id) {
		return userRepository.findById(id).orElse(null);
	}
	
	
	public User updateUser(Long id, User user) {
		
		User existingUser = userRepository.findById(id).orElse(null);
		
		if(existingUser != null) {
			
			existingUser.setUser(user.getUser()) ;
			existingUser.setAge(user.getAge()) ;
			existingUser.setGender(user.getGender()) ;
			existingUser.setHeight(user.getHeight()) ;
			existingUser.setWeight(user.getWeight()) ;
			existingUser.setGoal(user.getGoal()) ;
			existingUser.setDietType(user.getDietType()) ;
			
			return userRepository.save(existingUser) ; 
			
		}
		
		
		return null ;
		
	}
	
	public String deleteUser(Long id) {
		
		User existingUser = userRepository.findById(id).orElse(null);
		
		if(existingUser != null) {
			userRepository.delete(existingUser);
			
			return "User deleted successfully";
		}
		
		
		return "User Not Found" ;
	}
	
}






























