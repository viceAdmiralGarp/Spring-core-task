package com.spring.repository;

import com.spring.entity.Trainee;
import com.spring.entity.Trainer;
import com.spring.storage.Storage;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class TrainerDAO implements CrudRepository<Trainer, Long> {

	private final Storage storage;
	private static final Logger logger = LoggerFactory.getLogger(TrainerDAO.class);

	@Override
	public List<Trainer> getAll() {
		List<Trainer> trainers = storage.getEntities(Trainer.class);
		logger.info("Fetched {} trainers from storage.", trainers.size());
		return trainers;
	}

	@Override
	public Optional<Trainer> findEntityById(Long id) {
		return storage.getEntities(Trainer.class)
				.stream()
				.filter(trainer -> trainer.getUserId() == id)
				.findFirst();
	}

	@Override
	public void save(Trainer entity) {
		storage.addEntity(entity.getId(),entity);
		logger.info("Saved trainer with ID: {}", entity.getId());
	}

	@Override
	public void deleteEntity(Trainer entity) {
		Map<Class<?>, Map<Object, Object>> storageData = storage.getStorage();
		Map<Object, Object> objectMap = storageData.get(Trainee.class);
		objectMap.remove(entity.getUsername());
		logger.warn("No object map found for Trainer class in storage.");
	}
}
