package com.example.fitness.controller;

import java.util.Map;  // ✅ Correct import

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.fitness.dto.LoginRequest;
import com.example.fitness.dto.LoginResponse;
import com.example.fitness.entity.User;
import com.example.fitness.service.UserService;

@RestController
@RequestMapping("/auth")
//@CrossOrigin(origins = "${app.cors.allowed-origins:http://localhost:3000}")
//@CrossOrigin(origins = "*")
public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody User user) {
        try {
            if (userService.existsByEmail(user.getEmail())) {
                return ResponseEntity.badRequest().body(Map.of("message", "Email already registered"));
            }
            User newUser = userService.registerUser(user);
            return ResponseEntity.ok(newUser);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("message", e.getMessage()));
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        try {
            User user = userService.authenticate(request.getEmail(), request.getPassword());
            if (user != null) {
                LoginResponse response = new LoginResponse(
                    user.getId(), 
                    user.getEmail(), 
                    user.getUser(),
                    "Login successful"
                );
                return ResponseEntity.ok(response);
            } else {
                return ResponseEntity.status(401).body(Map.of("message", "Invalid email or password"));
            }
        } catch (Exception e) {
            return ResponseEntity.status(401).body(Map.of("message", "Invalid email or password"));
        }
    }
}