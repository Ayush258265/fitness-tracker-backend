package com.example.fitness.dto;

public class SmartRecommendationResponse {

	private String status;
	private double bmi;
	private ProgressResponse progress;
	private String finalAdvice;

	public SmartRecommendationResponse(String status, double bmi, ProgressResponse progress, String finalAdvice) {
		super();
		this.status = status;
		this.bmi = bmi;
		this.progress = progress;
		this.finalAdvice = finalAdvice;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public double getBmi() {
		return bmi;
	}

	public void setBmi(double bmi) {
		this.bmi = bmi;
	}

	public ProgressResponse getProgress() {
		return progress;
	}

	public void setProgress(ProgressResponse progress) {
		this.progress = progress;
	}

	public String getFinalAdvice() {
		return finalAdvice;
	}

	public void setFinalAdvice(String finalAdvice) {
		this.finalAdvice = finalAdvice;
	}

}
