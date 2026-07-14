package com.example.fitness.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.fitness.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

	User findByEmail(String email);

	boolean existsByEmail(String email);
}