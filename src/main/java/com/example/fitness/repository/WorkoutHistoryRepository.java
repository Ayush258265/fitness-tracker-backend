package com.example.fitness.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.fitness.entity.WorkoutHistory;

public interface WorkoutHistoryRepository extends JpaRepository<WorkoutHistory, Long> {

	List<WorkoutHistory> findByUserIdOrderByWorkoutDateDesc(Long userId);
	
	
	boolean existsByUserIdAndWorkoutDate(Long userId, LocalDate workoutDate);
	
	@Query("SELECT COUNT(DISTINCT w.workoutDate) FROM WorkoutHistory w WHERE w.userId = :userId AND w.workoutDate >= :startDate")
	Integer countWorkoutDaysInLastWeek(@Param("userId") Long userId , @Param("startDate") LocalDate startDate) ;


	List<WorkoutHistory> findByUserIdAndWorkoutDate(Long userId, LocalDate date);
	
	
}
