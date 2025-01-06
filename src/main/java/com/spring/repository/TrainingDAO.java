package com.spring.repository;


import com.spring.entity.Training;
import com.spring.storage.Storage;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class TrainingDAO implements CrudRepository<Training, Long> {

	private final Storage storage;
	private static final Logger logger = LoggerFactory.getLogger(TrainingDAO.class);

	@Override
	public List<Training> getAll() {
		List<Training> trainings = storage.getEntities(Training.class);
		logger.info("Fetched {} trainings from storage.", trainings.size());
		return trainings;
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
		logger.info("Saved training with ID: {}", entity.getId());
	}

	@Override
	public void deleteEntity(Training entity) {
		Map<Class<?>, Map<Object, Object>> storageData = storage.getStorage();
		Map<Object, Object> objectMap = storageData.get(Training.class);
		objectMap.remove(entity.getId());
		logger.info("Deleted training with ID: {}", entity.getId());
	}
}
