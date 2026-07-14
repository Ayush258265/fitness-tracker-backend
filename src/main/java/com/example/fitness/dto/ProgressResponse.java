package com.example.fitness.dto;

public class ProgressResponse {

	private String weightTrend;
	private double avgCaloriesConsumed;
	private double avgCaloriesBurned;
	private String calorieBalance;
	private String suggestion;

	public ProgressResponse(String weightTrend, double avgCaloriesConsumed, double avgCaloriesBurned,
			String calorieBalance, String suggestion) {
		super();
		this.weightTrend = weightTrend;
		this.avgCaloriesConsumed = avgCaloriesConsumed;
		this.avgCaloriesBurned = avgCaloriesBurned;
		this.calorieBalance = calorieBalance;
		this.suggestion = suggestion;
	}

	public String getWeightTrend() {
		return weightTrend;
	}

	public void setWeightTrend(String weightTrend) {
		this.weightTrend = weightTrend;
	}

	public double getAvgCaloriesConsumed() {
		return avgCaloriesConsumed;
	}

	public void setAvgCaloriesConsumed(double avgCaloriesConsumed) {
		this.avgCaloriesConsumed = avgCaloriesConsumed;
	}

	public double getAvgCaloriesBurned() {
		return avgCaloriesBurned;
	}

	public void setAvgCaloriesBurned(double avgCaloriesBurned) {
		this.avgCaloriesBurned = avgCaloriesBurned;
	}

	public String getCalorieBalance() {
		return calorieBalance;
	}

	public void setCalorieBalance(String calorieBalance) {
		this.calorieBalance = calorieBalance;
	}

	public String getSuggestion() {
		return suggestion;
	}

	public void setSuggestion(String suggestion) {
		this.suggestion = suggestion;
	}

}
