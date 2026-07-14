package com.example.fitness.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.fitness.entity.DailyLog;

@Repository
public interface DailyLogRepository extends JpaRepository<DailyLog, Long> {

	List<DailyLog> findByUserId(Long userId);

}