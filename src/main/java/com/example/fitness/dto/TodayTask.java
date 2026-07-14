package com.example.fitness.dto;

import java.util.List;

public class TodayTask {

	private String workout;
	private Diet diet;
	private Water water;
	private Calorie calorie;

	
	private List<WorkoutPlanDTO> workoutPlan;

	public List<WorkoutPlanDTO> getWorkoutPlan() { return workoutPlan; }
	public void setWorkoutPlan(List<WorkoutPlanDTO> workoutPlan) { this.workoutPlan = workoutPlan; }
	
	public TodayTask() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TodayTask(String workout, Diet diet, Water water, Calorie calorie) {
		super();
		this.workout = workout;
		this.diet = diet;
		this.water = water;
		this.calorie = calorie;
	}

	public String getWorkout() {
		return workout;
	}

	public void setWorkout(String workout) {
		this.workout = workout;
	}

	public Diet getDiet() {
		return diet;
	}

	public void setDiet(Diet diet) {
		this.diet = diet;
	}

	public Water getWater() {
		return water;
	}

	public void setWater(Water water) {
		this.water = water;
	}

	public Calorie getCalorie() {
		return calorie;
	}

	public void setCalorie(Calorie calorie) {
		this.calorie = calorie;
	}

}
