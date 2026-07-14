package com.example.fitness.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.fitness.entity.DailyLog;
import com.example.fitness.entity.User;
import com.example.fitness.repository.UserRepository;
import com.example.fitness.repository.DailyLogRepository;

@Service
public class DailyLogService {

	@Autowired
	private UserRepository userRepository ;
	
	@Autowired
	private DailyLogRepository logRepository ;
	
	public DailyLog addLog(Long userId , DailyLog log) {
		
		User user = userRepository.findById(userId).orElse(null);
		
		if(user == null) {
			return null ;
		}
		log.setUser(user);
		return logRepository.save(log);
		
	}
	
	public List<DailyLog> getUserLogs(Long userId) {
		return logRepository.findByUserId(userId);
	}
	
}
