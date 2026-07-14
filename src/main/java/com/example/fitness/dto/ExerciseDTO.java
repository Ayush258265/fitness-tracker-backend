package com.example.fitness.dto;

public class ExerciseDTO {

	private Long id;
	private Integer calories;
	private String exercise;
	private String time;

	public ExerciseDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ExerciseDTO(Long id, Integer calories, String exercise, String time) {
		super();
		this.id = id;
		this.calories = calories;
		this.exercise = exercise;
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

	public String getExercise() {
		return exercise;
	}

	public void setExercise(String exercise) {
		this.exercise = exercise;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

}
