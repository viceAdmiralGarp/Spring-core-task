package com.spring.repository;

import com.spring.entity.Trainee;
import com.spring.storage.Storage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;


@Repository
@RequiredArgsConstructor
public class TraineeDAO implements CrudRepository<Trainee, Long> {

	private final Storage storage;

	@Override
	public List<Trainee> getAll() {
		return storage.getEntities(Trainee.class);
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
	}

	@Override
	public void deleteEntityById(Trainee entity) {
		Map<Class<?>, Map<Object, Object>> storageData = storage.getStorage();
		Map<Object, Object> objectMap = storageData.get(Trainee.class);
		objectMap.remove(entity.getUsername());
	}
}
