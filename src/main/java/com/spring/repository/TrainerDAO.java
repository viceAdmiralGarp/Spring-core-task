package com.spring.repository;

import com.spring.entity.Trainer;
import com.spring.storage.Storage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class TrainerDAO implements CrudRepository<Trainer, Long> {

	private final Storage storage;
	private static final String KEY = "Trainer";

	@Override
	public List<Trainer> getAll() {
		Map<String, Map<String, Object>> storageData = storage.getStorage();
		Map<String, Object> trainersMap = storageData.get(KEY);

		if (trainersMap == null) {
			return Collections.emptyList();
		}

		return trainersMap.values()
				.stream()
				.map(value -> (Trainer) value)
				.toList();
	}

	@Override
	public Optional<Trainer> findEntityById(Long id) {
		Map<String, Map<String, Object>> storageData = storage.getStorage();
		Map<String, Object> trainersMap = storageData.get(KEY);

		return trainersMap.values()
				.stream()
				.map(value -> (Trainer) value)
				.filter(trainer -> trainer.getUserId() == id)
				.findFirst();
	}

	@Override
	public Trainer save(String userName, Trainer entity) {
		return (Trainer) storage.getStorage().get(KEY).put(userName, entity);
	}

	@Override
	public void deleteEntityById(Long id) {
		Trainer trainer = findEntityById(id)
				.orElseThrow(() -> new IllegalArgumentException("Trainer with ID %s was not found".formatted(id)));

		storage.getStorage().get(KEY).remove(trainer.getUsername());
	}
}
