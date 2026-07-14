package com.example.fitness.dto;

import java.util.List;

public class CalorieDataDTO {

	private Integer consumed;
	private Integer burned;
	private Integer goal;
	private String date;

	private List<MealDTO> meals;
	private List<ExerciseDTO> exercises;

	public CalorieDataDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CalorieDataDTO(Integer consumed, Integer burned, Integer goal, String date, List<MealDTO> meals,
			List<ExerciseDTO> exercises) {
		super();
		this.consumed = consumed;
		this.burned = burned;
		this.goal = goal;
		this.date = date;
		this.meals = meals;
		this.exercises = exercises;
	}

	public Integer getConsumed() {
		return consumed;
	}

	public void setConsumed(Integer consumed) {
		this.consumed = consumed;
	}

	public Integer getBurned() {
		return burned;
	}

	public void setBurned(Integer burned) {
		this.burned = burned;
	}

	public Integer getGoal() {
		return goal;
	}

	public void setGoal(Integer goal) {
		this.goal = goal;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public List<MealDTO> getMeals() {
		return meals;
	}

	public void setMeals(List<MealDTO> meals) {
		this.meals = meals;
	}

	public List<ExerciseDTO> getExercises() {
		return exercises;
	}

	public void setExercises(List<ExerciseDTO> exercises) {
		this.exercises = exercises;
	}

}
