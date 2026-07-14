package com.example.fitness.dto;

public class MealDTO {

	private Long id;
	private Integer calories;
	private String meal;
	private String time;

	public MealDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public MealDTO(Long id, Integer calories, String meal, String time) {
		super();
		this.id = id;
		this.calories = calories;
		this.meal = meal;
		this.time = time;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getCalories() {
		return calories;
	}

	public void setCalories(Integer calories) {
		this.calories = calories;
	}

	public String getMeal() {
		return meal;
	}

	public void setMeal(String meal) {
		this.meal = meal;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

}
