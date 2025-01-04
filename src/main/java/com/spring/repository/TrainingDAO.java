package com.spring.repository;

import com.spring.entity.Trainee;
import com.spring.entity.Training;
import com.spring.storage.Storage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class TrainingDAO implements CrudRepository<Training, Long> {

	private final Storage storage;

	@Override
	public List<Training> getAll() {
		return storage.getEntities(Training.class);
	}

	@Override
	public Optional<Training> findEntityById(Long id) {
		return storage.getEntities(Training.class)
				.stream()
				.filter(training -> Objects.equals(training.getId(), id))
				.findFirst();
	}

	@Override
	public void save(Training entity) {
		storage.addEntity(entity.getId(),entity);
	}

	@Override
	public void deleteEntity(Training entity) {
		Map<Class<?>, Map<Object, Object>> storageData = storage.getStorage();
		Map<Object, Object> objectMap = storageData.get(Trainee.class);
		objectMap.remove(entity);
	}
}
