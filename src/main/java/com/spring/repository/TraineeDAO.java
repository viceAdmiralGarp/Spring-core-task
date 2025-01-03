package com.spring.repository;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.spring.data.DataLoader;
import com.spring.entity.Trainee;
import com.spring.exception.EntityNotFoundException;
import com.spring.storage.Storage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.List;
import java.util.Optional;


@Repository
@RequiredArgsConstructor
public class TraineeDAO implements CrudRepository<Trainee, Long> {

	private final Storage storage;

	@Override
	public List<Trainee> getAll() {
		return storage.getEntities(Trainee.class).stream().toList();
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
		storage.addEntity(entity.getId(),entity);
	}

	@Override
	public void deleteEntityById(Long id) {
		Trainee trainee = findEntityById(id)
				.orElseThrow(() -> new EntityNotFoundException("Trainer with ID %s not found".formatted(id)));

		storage.getEntities(Trainee.class).remove(trainee);
	}
}
