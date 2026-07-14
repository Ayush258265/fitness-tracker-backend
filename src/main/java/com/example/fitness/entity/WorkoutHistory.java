package com.example.fitness.entity;

import java.time.LocalDate;

import jakarta.persistence.*;

@Entity
@Table(name = "workout_history")
public class WorkoutHistory {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Long userId;
	private LocalDate workoutDate;
	private String workoutName;
	private Integer duration;
	private Integer caloriesBurned;
	private Integer exercisesCompleted;

	public WorkoutHistory() {
		super();
		// TODO Auto-generated constructor stub
	}

	public WorkoutHistory(Long userId, LocalDate workoutDate, String workoutName, Integer duration,
			Integer caloriesBurned, Integer exercisesCompleted) {
		super();
		this.userId = userId;
		this.workoutDate = workoutDate;
		this.workoutName = workoutName;
		this.duration = duration;
		this.caloriesBurned = caloriesBurned;
		this.exercisesCompleted = exercisesCompleted;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public LocalDate getWorkoutDate() {
		return workoutDate;
	}

	public void setWorkoutDate(LocalDate workoutDate) {
		this.workoutDate = workoutDate;
	}

	public String getWorkoutName() {
		return workoutName;
	}

	public void setWorkoutName(String workoutName) {
		this.workoutName = workoutName;
	}

	public Integer getDuration() {
		return duration;
	}

	public void setDuration(Integer duration) {
		this.duration = duration;
	}

	public Integer getCaloriesBurned() {
		return caloriesBurned;
	}

	public void setCaloriesBurned(Integer caloriesBurned) {
		this.caloriesBurned = caloriesBurned;
	}

	public Integer getExercisesCompleted() {
		return exercisesCompleted;
	}

	public void setExercisesCompleted(Integer exercisesCompleted) {
		this.exercisesCompleted = exercisesCompleted;
	}

}
