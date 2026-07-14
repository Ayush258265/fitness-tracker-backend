package com.example.fitness.dto;

public class AddConsumedRequestDTO {

	private Integer consumed;
	private MealDTO meal;

	public AddConsumedRequestDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AddConsumedRequestDTO(Integer consumed, MealDTO meal) {
		super();
		this.consumed = consumed;
		this.meal = meal;
	}

	public Integer getConsumed() {
		return consumed;
	}

	public void setConsumed(Integer consumed) {
		this.consumed = consumed;
	}

	public MealDTO getMeal() {
		return meal;
	}

	public void setMeal(MealDTO meal) {
		this.meal = meal;
	}

}
