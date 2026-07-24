//package com.example.fitness.repository;
//
//import java.time.LocalDate;
//import java.util.List;
//
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.query.Param;
//
//import com.example.fitness.entity.WorkoutHistory;
//
//public interface WorkoutHistoryRepository extends JpaRepository<WorkoutHistory, Long> {
//
//	List<WorkoutHistory> findByUserIdOrderByWorkoutDateDesc(Long userId);
//	
//	
//	boolean existsByUserIdAndWorkoutDate(Long userId, LocalDate workoutDate);
//	
//	@Query("SELECT COUNT(DISTINCT w.workoutDate) FROM WorkoutHistory w WHERE w.userId = :userId AND w.workoutDate >= :startDate")
//	Integer countWorkoutDaysInLastWeek(@Param("userId") Long userId , @Param("startDate") LocalDate startDate) ;
//
//
//	List<WorkoutHistory> findByUserIdAndWorkoutDate(Long userId, LocalDate date);
//	
//	
//}

//update code

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
    Integer countWorkoutDaysInLastWeek(@Param("userId") Long userId, @Param("startDate") LocalDate startDate);

    List<WorkoutHistory> findByUserIdAndWorkoutDate(Long userId, LocalDate date);

    // ✅ FIXED QUERY - Corrected spelling and syntax
    @Query("SELECT w.workoutDate, SUM(w.caloriesBurned), SUM(w.duration), COUNT(w.id) " +
           "FROM WorkoutHistory w " +
           "WHERE w.userId = :userId " +
           "AND w.workoutDate BETWEEN :startDate AND :endDate " +
           "GROUP BY w.workoutDate")
    List<Object[]> getWeeklySummaryData(@Param("userId") Long userId,
                                        @Param("startDate") LocalDate startDate,
                                        @Param("endDate") LocalDate endDate);

    // ✅ FIXED QUERY - Corrected spelling
    @Query("SELECT COALESCE(SUM(w.caloriesBurned), 0), COALESCE(SUM(w.duration), 0), COUNT(DISTINCT w.workoutDate) " +
           "FROM WorkoutHistory w " +
           "WHERE w.userId = :userId " +
           "AND w.workoutDate BETWEEN :startDate AND :endDate")
    Object[] getWeeklyTotals(@Param("userId") Long userId,
                             @Param("startDate") LocalDate startDate,
                             @Param("endDate") LocalDate endDate);
}

















