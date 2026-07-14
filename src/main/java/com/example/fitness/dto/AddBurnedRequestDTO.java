package com.example.fitness.dto;

public class AddBurnedRequestDTO {

	private Integer burned;
	private ExerciseDTO exercise;

	public AddBurnedRequestDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AddBurnedRequestDTO(Integer burned, ExerciseDTO exercise) {
		super();
		this.burned = burned;
		this.exercise = exercise;
	}

	public Integer getBurned() {
		return burned;
	}

	public void setBurned(Integer burned) {
		this.burned = burned;
	}

	public ExerciseDTO getExercise() {
		return exercise;
	}

	public void setExercise(ExerciseDTO exercise) {
		this.exercise = exercise;
	}

}
