package com.spring.storage;

import com.spring.entity.Trainee;
import com.spring.entity.Trainer;
import com.spring.entity.Training;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Getter
@Component
@RequiredArgsConstructor
public class Storage {

	private final Map<String, Map<String, Object>> storage = new HashMap<>();

	public void addTrainer(String username, Trainer trainer) {
		storage.computeIfAbsent("Trainer", k -> new HashMap<>()).put(username, trainer);
	}

	public void addTrainee(String username, Trainee trainee) {
		storage.computeIfAbsent("Trainee", k -> new HashMap<>()).put(username, trainee);
	}

	public void addTraining(String trainingName, Training training) {
		storage.computeIfAbsent("Training", k -> new HashMap<>()).put(trainingName, training);
	}
}
