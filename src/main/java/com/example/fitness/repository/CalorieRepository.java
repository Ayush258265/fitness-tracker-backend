package com.example.fitness.repository;

import java.util.List;
import java.time.LocalDate;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.fitness.entity.CalorieLog;

public interface CalorieRepository extends JpaRepository<CalorieLog, Long> {

	List<CalorieLog> findByUserIdAndLogDateOrderByCreatedAtDesc(Long userId, LocalDate date);

	// Get total consumed calories for a user on a specific date
	@Query("SELECT COALESCE(SUM(c.calories), 0) FROM CalorieLog c WHERE c.userId = :userId AND c.logDate = :logDate AND c.type = 'CONSUMED'")

	Integer getTotalConsumedByDate(@Param("userId") Long userId, @Param("logDate") LocalDate logDate);

	// Get total burned calories for a user on a specific date
	@Query("SELECT COALESCE(SUM(c.calories), 0) FROM CalorieLog c WHERE c.userId = :userId AND c.logDate = :logDate AND c.type = 'BURNED'")
	Integer getTotalBurnedByDate(@Param("userId") Long userId, @Param("logDate") LocalDate logDate);

	List<CalorieLog> findByUserIdOrderByLogDateDescCreatedAtDesc(Long userId);

	// Get calorie logs for a date range
	List<CalorieLog> findByUserIdAndLogDateBetween(Long userId, LocalDate startDate, LocalDate endDate);

	// Delete all logs for a user
	void deleteByUserId(Long userId);

}
