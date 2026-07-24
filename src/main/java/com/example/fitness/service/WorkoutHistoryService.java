package com.example.fitness.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.fitness.entity.WorkoutHistory;
import com.example.fitness.repository.WorkoutHistoryRepository;

@Service
public class WorkoutHistoryService {

	@Autowired
	private WorkoutHistoryRepository workoutHistoryRepository;

	@Autowired
	private CalorieService calorieService;

	public WorkoutHistory saveWorkout(Long userId, String workoutName, Integer duration, Integer exercisesCompleted,
			Integer totalCalories) {

// Save workout history
		WorkoutHistory history = new WorkoutHistory(userId, LocalDate.now(), workoutName, duration, totalCalories,
				exercisesCompleted);

		WorkoutHistory saved = workoutHistoryRepository.save(history);

// Also add a BURNED entry to calorie_logs

		if (totalCalories != null && totalCalories > 0) {
			calorieService.addBurnedCalories(userId, totalCalories, workoutName);
		}

		return saved;
	}

	public List<WorkoutHistory> getUserWorkoutHistory(Long userId) {
		return workoutHistoryRepository.findByUserIdOrderByWorkoutDateDesc(userId);
	}

	public Map<String, Object> getWorkoutStats(Long userId) {

		Map<String, Object> stats = new HashMap<>();

		List<WorkoutHistory> history = workoutHistoryRepository.findByUserIdOrderByWorkoutDateDesc(userId);

		int totalWorkouts = history.size();
		int totalCalories = history.stream().mapToInt(WorkoutHistory::getCaloriesBurned).sum();
		int currentStreak = calculateCurrentStreak(userId);

		stats.put("totalWorkouts", totalWorkouts);
		stats.put("totalCalories", totalCalories);
		stats.put("currentStreak", currentStreak);

		List<Map<String, Object>> weeklyActivity = getWeeklyActivity(userId);

		stats.put("totalWorkouts", totalWorkouts);
		stats.put("totalCalories", totalCalories);
		stats.put("currentStreak", currentStreak);
		stats.put("weeklyActivity", weeklyActivity);

		return stats;

	}

	private int calculateCurrentStreak(Long userId) {

		LocalDate today = LocalDate.now();

		int streak = 0;
		for (int i = 0; i < 30; i++) {
			LocalDate checkDate = today.minusDays(i);

			boolean hasWorkout = workoutHistoryRepository.existsByUserIdAndWorkoutDate(userId, checkDate);

			if (hasWorkout) {
				streak++;
			} else {
				break;
			}

		}
		return streak;

	}

	private List<Map<String, Object>> getWeeklyActivity(Long userId) {

		List<Map<String, Object>> weekly = new ArrayList<>();

		LocalDate today = LocalDate.now();

		for (int i = 6; i >= 0; i--) {
			LocalDate date = today.minusDays(i);

			boolean hasWorkout = workoutHistoryRepository.existsByUserIdAndWorkoutDate(userId, date);

			Map<String, Object> day = new HashMap<>();
			day.put("date", date.toString());
			day.put("day", date.getDayOfWeek().toString().substring(0, 3));
			day.put("completed", hasWorkout);
			weekly.add(day);
		}

		return weekly;

	}

	// Get weekly workout summary for charts
	public Map<String, Object> getWeeklySummary(Long userId) {
		Map<String, Object> summary = new HashMap<>();

		LocalDate today = LocalDate.now();

		// Get last 7 days data
		List<Map<String, Object>> weeklyWorkouts = new ArrayList<>();

		int totalWorkoutsThisWeek = 0;
		int totalCaloriesThisWeek = 0;

		for (int i = 6; i >= 0; i--) {
			LocalDate date = today.minusDays(i);

			List<WorkoutHistory> dayWorkouts = workoutHistoryRepository.findByUserIdAndWorkoutDate(userId, date);

			Map<String, Object> dayData = new HashMap<>();

			dayData.put("day", date.getDayOfWeek().toString().substring(0, 3));
			dayData.put("date", date.toString());
			dayData.put("workoutCount", dayWorkouts.size());
			dayData.put("calories", dayWorkouts.stream().mapToInt(WorkoutHistory::getCaloriesBurned).sum());

			weeklyWorkouts.add(dayData);
			totalWorkoutsThisWeek = totalWorkoutsThisWeek + dayWorkouts.size();
			totalCaloriesThisWeek = totalCaloriesThisWeek
					+ dayWorkouts.stream().mapToInt(WorkoutHistory::getCaloriesBurned).sum();

		}

		// Get current streak
		int currentStreak = calculateCurrentStreak(userId);

		// Get total stats
		List<WorkoutHistory> allWorkouts = workoutHistoryRepository.findByUserIdOrderByWorkoutDateDesc(userId);

		int totalWorkouts = allWorkouts.size();
		int totalCalories = allWorkouts.stream().mapToInt(WorkoutHistory::getCaloriesBurned).sum();

		summary.put("totalWorkouts ", weeklyWorkouts);
		summary.put("currentStreak", currentStreak);
		summary.put("totalWorkouts", totalWorkouts);
		summary.put("totalCalories", totalCalories);
		summary.put("bestStreak", getBestStreak(userId));

		return summary;
	}

	// Get best streak ever

	private int getBestStreak(Long userId) {

		List<WorkoutHistory> allWorkouts = workoutHistoryRepository.findByUserIdOrderByWorkoutDateDesc(userId);

		if (allWorkouts.isEmpty())
			return 0;

		int bestStreak = 1;
		int currentStreak = 1;

		for (int i = 0; i < allWorkouts.size() - 1; i++) {
			LocalDate currentDate = allWorkouts.get(i).getWorkoutDate();
			LocalDate nextDate = allWorkouts.get(i + 1).getWorkoutDate();

			if (currentDate.minusDays(1).equals(nextDate)) {
				currentStreak++;

				bestStreak = Math.max(currentStreak, bestStreak);
			} else {
				currentStreak = 1;
			}
		}

		return bestStreak;
	}

	// ==================== WEEKLY SUMMARY ====================
	
	public Map<String, Object> getWeeklyResults(Long userId) {
		Map<String, Object> result = new HashMap<>();

		// Get current week (Monday to Sunday)
		LocalDate today = LocalDate.now();
		LocalDate startOfWeek = today.with(java.time.DayOfWeek.MONDAY);
		LocalDate endOfWeek = today.with(java.time.DayOfWeek.SUNDAY);

		// Get weekly totals
		Object[] totals = workoutHistoryRepository.getWeeklyTotals(userId, startOfWeek, endOfWeek);
		Long totalCalories = totals[0] != null ? ((Number) totals[0]).longValue() : 0L;
		Long totalDuration = totals[1] != null ? ((Number) totals[1]).longValue() : 0L;
		Long distinctDays = totals[2] != null ? ((Number) totals[2]).longValue() : 0L;

		// Get daily breakdown
		List<Object[]> dailyData = workoutHistoryRepository.getWeeklySummaryData(userId, startOfWeek, endOfWeek);

		// Build daily breakdown map (Mon to Sun)
		Map<String, Map<String, Object>> dayMap = new HashMap<>();
		String[] dayNames = { "Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun" };

		// Initialize all days with zeros
		for (String day : dayNames) {
			Map<String, Object> dayStats = new HashMap<>();
			dayStats.put("calories", 0L);
			dayStats.put("duration", 0L);
			dayMap.put(day, dayStats);
		}

		// Populate with actual data
		for (Object[] row : dailyData) {
			LocalDate date = (LocalDate) row[0];
			String dayName = date.getDayOfWeek().toString().substring(0, 3);
			String formattedDay = dayName.substring(0, 1) + dayName.substring(1, 3).toLowerCase(); // MON -> Mon

			Long calories = row[1] != null ? ((Number) row[1]).longValue() : 0L;
			Long duration = row[2] != null ? ((Number) row[2]).longValue() : 0L;

			// ✅ FIXED: Update the existing day entry
			Map<String, Object> dayStats = dayMap.get(formattedDay);
			dayStats.put("calories", calories);
			dayStats.put("duration", duration);
		}

		// Build daily breakdown list in correct order (Mon to Sun)
		List<Map<String, Object>> dailyBreakdown = new ArrayList<>();
		for (String day : dayNames) {
			Map<String, Object> entry = new HashMap<>();
			entry.put("day", day);
			entry.put("calories", dayMap.get(day).get("calories"));
			entry.put("duration", dayMap.get(day).get("duration"));
			dailyBreakdown.add(entry);
		}

		// Calculate current streak
		int currentStreak = calculateCurrentStreak(userId);

		// Build final response
		result.put("totalWorkouts", distinctDays.intValue());
		result.put("totalCalories", totalCalories);
		result.put("totalDuration", totalDuration);
		result.put("currentStreak", currentStreak);
		result.put("dailyBreakdown", dailyBreakdown);

		return result;
	}

}
