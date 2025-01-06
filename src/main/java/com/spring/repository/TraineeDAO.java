package com.spring.repository;

import com.spring.data.DataLoader;
import com.spring.entity.Trainee;
import com.spring.storage.Storage;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;


@Repository
@RequiredArgsConstructor
public class TraineeDAO implements CrudRepository<Trainee, Long> {

	private final Storage storage;
	private static final Logger logger = LoggerFactory.getLogger(TraineeDAO.class);

	@Override
	public List<Trainee> getAll() {
		List<Trainee> trainees = storage.getEntities(Trainee.class);
		logger.info("Fetched {} trainees from storage.", trainees.size());
		return trainees;
	}

	@Override
	public Optional<Trainee> findEntityById(Long id) {
		return storage.getEntities(Trainee.class)
				.stream()
				.filter(trainee -> trainee.getUserId() == id)
				.findFirst();
	}

	@Override
	public void save(Trainee entity) {
		storage.addEntity(entity.getId(), entity);
		logger.info("Saved trainee with ID: {}", entity.getId());
	}

	@Override
	public void deleteEntity(Trainee entity) {
		Map<Class<?>, Map<Object, Object>> storageData = storage.getStorage();
		Map<Object, Object> objectMap = storageData.get(Trainee.class);
		objectMap.remove(entity.getUsername());
		logger.info("Deleted trainee with ID: {}", entity.getId());
	}
}
