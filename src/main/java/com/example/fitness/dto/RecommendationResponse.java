package com.example.fitness.dto;

public class RecommendationResponse {

	private String exercise;
	private String diet;
	private String advice;
	private String intensity;

	public RecommendationResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	public RecommendationResponse(String exercise, String diet, String advice, String intensity) {
		super();
		this.exercise = exercise;
		this.diet = diet;
		this.advice = advice;
		this.intensity = intensity;
	}

	public String getExercise() {
		return exercise;
	}

	public void setExercise(String exercise) {
		this.exercise = exercise;
	}

	public String getDiet() {
		return diet;
	}

	public void setDiet(String diet) {
		this.diet = diet;
	}

	public String getAdvice() {
		return advice;
	}

	public void setAdvice(String advice) {
		this.advice = advice;
	}

	public String getIntensity() {
		return intensity;
	}

	public void setIntensity(String intensity) {
		this.intensity = intensity;
	}

}
