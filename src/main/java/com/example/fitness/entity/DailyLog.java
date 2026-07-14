package com.example.fitness.entity;

import java.time.LocalDate;

import jakarta.persistence.*;

@Entity
@Table(name = "daily_logs")
public class DailyLog {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private LocalDate date;
	private double caloriesConsumed;
	private double caloriesBurned;
	private double weight;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	public DailyLog() {
		super();
	}

	// Getters and Setters
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public double getCaloriesConsumed() {
		return caloriesConsumed;
	}

	public void setCaloriesConsumed(double caloriesConsumed) {
		this.caloriesConsumed = caloriesConsumed;
	}

	public double getCaloriesBurned() {
		return caloriesBurned;
	}

	public void setCaloriesBurned(double caloriesBurned) {
		this.caloriesBurned = caloriesBurned;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}