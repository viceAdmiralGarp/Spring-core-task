package com.spring.repository;

import com.spring.entity.Trainee;
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
public class TraineeDAO implements CrudRepository<Trainee, Long> {

	private final Storage storage;

	@Value("${storage.trainees.key}")
	private String key;

	@Override
	public List<Trainee> getAll() {
		Map<String, Map<String, Object>> storageData = storage.getStorage();
		Map<String, Object> traineeMap = storageData.get(key);

		if (traineeMap == null) return Collections.emptyList();

		return traineeMap.values()
				.stream()
				.map(value -> (Trainee) value)
				.toList();
	}

	@Override
	public Optional<Trainee> findEntityById(Long id) {
		Map<String, Map<String, Object>> storageData = storage.getStorage();
		Map<String, Object> traineeMap = storageData.get(key);

		return traineeMap.values()
				.stream()
				.map(value -> (Trainee) value)
				.filter(trainee -> trainee.getUserId() == id)
				.findFirst();
	}

	@Override
	public Trainee save(String userName, Trainee entity) {
		return (Trainee) storage.getStorage().get(key).put(userName, entity);
	}

	@Override
	public void deleteEntityById(Long id) {
		Trainee trainee = findEntityById(id)
				.orElseThrow(() -> new IllegalArgumentException("Trainer with ID %s was not found".formatted(id)));

		storage.getStorage().get(key).remove(trainee.getUsername());
	}
}
