package com.spring.repository;

import com.spring.entity.Training;
import com.spring.storage.Storage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class TrainingDAO implements CrudRepository<Training, Long> {

	private final Storage storage;

	@Value("${storage.trainings.key}")
	private String key;

	@Override
	public List<Training> getAll() {
		Map<String, Map<String, Object>> storageData = storage.getStorage();
		Map<String, Object> trainingMap = storageData.get(key);

		if (trainingMap == null) return Collections.emptyList();

		return trainingMap.values()
				.stream()
				.map(value -> (Training) value)
				.toList();
	}

	@Override
	public Optional<Training> findEntityById(Long id) {
		Map<String, Map<String, Object>> storageData = storage.getStorage();
		Map<String, Object> trainingMap = storageData.get(key);

		return trainingMap.values()
				.stream()
				.map(value -> (Training) value)
				.filter(training -> training.getTrainingId() == id)
				.findFirst();
	}

	@Override
	public Training save(String id, Training entity) {
		if (Long.parseLong(id) != entity.getTrainingId())
			throw new IllegalArgumentException("ID %s does not match training ID %s".formatted(id,entity.getTrainingId()));

		return (Training) storage.getStorage().get(key).put(id, entity);
	}

	@Override
	public void deleteEntityById(Long id) {
		Training training = findEntityById(id)
				.orElseThrow(() -> new IllegalArgumentException("Trainer with ID %s was not found".formatted(id)));

		storage.getStorage().get(key).remove(String.valueOf(training.getTrainingId()));
	}
}
