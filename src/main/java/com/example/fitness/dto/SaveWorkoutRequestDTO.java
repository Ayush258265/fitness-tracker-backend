package com.example.fitness.dto;

public class SaveWorkoutRequestDTO {

	private Long userId;
	private String workoutName;
	private Integer duration;
//	private Integer caloriesBurned;
	private Integer exercisesCompleted;

	public SaveWorkoutRequestDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SaveWorkoutRequestDTO(Long userId, String workoutName, Integer duration, 
			Integer exercisesCompleted) {
		super();
		this.userId = userId;
		this.workoutName = workoutName;
		this.duration = duration;
//		this.caloriesBurned = caloriesBurned;
		this.exercisesCompleted = exercisesCompleted;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
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

//	public Integer getCaloriesBurned() {
//		return caloriesBurned;
//	}
//
//	public void setCaloriesBurned(Integer caloriesBurned) {
//		this.caloriesBurned = caloriesBurned;
//	}

	public Integer getExercisesCompleted() {
		return exercisesCompleted;
	}

	public void setExercisesCompleted(Integer exercisesCompleted) {
		this.exercisesCompleted = exercisesCompleted;
	}

}
