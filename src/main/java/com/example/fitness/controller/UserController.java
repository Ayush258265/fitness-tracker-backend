package com.example.fitness.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.fitness.entity.User;
//import com.example.fitness.repository.UserRepository;
import com.example.fitness.service.UserService;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping(path = "/user")
@Validated
//@CrossOrigin(origins = "${app.cors.allowed-origins:http://localhost:3000}")
@CrossOrigin(origins = "*")
public class UserController {

//	private final UserRepository userRepository;

	@Autowired
	private UserService userService;
	
	@GetMapping("/health")
	public String health() {
	    return "Backend is running!";
	}

//	UserController(UserRepository userRepository) {
//		this.userRepository = userRepository;
//	}

//	@PostMapping("/create/user")
//	public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
//
//		return ResponseEntity.ok(userService.createUser(user));
//	}

	@PostMapping("/create/user")
	public ResponseEntity<?> createUser(@Valid @RequestBody User user, BindingResult result) {
		if (result.hasErrors()) {
			return ResponseEntity.badRequest().body(result.getAllErrors());
		}
		try {
			return ResponseEntity.ok(userService.createUser(user));
		} catch (DataIntegrityViolationException e) {
			return ResponseEntity.badRequest().body("Email already exists");
		}
	}

	@GetMapping("get/all/users")
	public ResponseEntity<List<User>> getAllUsers(User user) {
		return ResponseEntity.ok(userService.getAllUsers(user));
	}

	@GetMapping("get/user/{id}")
	public ResponseEntity<?> getUserById(@PathVariable Long id) {
		User user = userService.getUserById(id);

		if (user != null) {
			return ResponseEntity.ok(user);
		}
		return ResponseEntity.badRequest().body("user not found");

	}

	@PutMapping("update/user/{id}")
	public ResponseEntity<?> updateUser(@PathVariable Long id, @RequestBody User user) {

		User updatedUser = userService.updateUser(id, user);

		if (updatedUser != null) {
			return ResponseEntity.ok(updatedUser);
		}
		return ResponseEntity.badRequest().body("User not found");

	}

	@DeleteMapping("/delete/user/{id}")
	public ResponseEntity<String> deleteUser(@PathVariable Long id) {
		return ResponseEntity.ok(userService.deleteUser(id));
	}

}
