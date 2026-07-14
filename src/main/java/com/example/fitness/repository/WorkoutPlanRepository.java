package com.example.fitness.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.fitness.entity.WorkoutPlan;

public interface WorkoutPlanRepository extends JpaRepository<WorkoutPlan, Long> {

	List<WorkoutPlan> findByBmiCategoryOrderByPhase(String bmiCategory) ;
	
}
