package com.spring.repository;

import com.spring.exception.EntityExistByUserName;
import com.spring.entity.Trainer;
import com.spring.exception.EntityDoesntExistByUserName;
import com.spring.storage.Storage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.Map;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@Repository
@RequiredArgsConstructor
public class TrainerDAO implements CrudRepository<Trainer, String> {

	private final Storage storage;

	@Override
	public List<Trainer> getAll() {
		return storage.getStorage()
				.getOrDefault("Trainer", Map.of())
				.values()
				.stream()
				.filter(Trainer.class::isInstance)
				.map(Trainer.class::cast)
				.collect(toList());
	}

	@Override
	public Optional<Trainer> findEntityById(String s) {
		return Optional.empty();
	}


	@Override
	public Trainer save(Trainer entity) {
		return null;
	}

	@Override
	public void deleteEntityByUserName(String userName) {

	}

	@Override
	public void updateUserName(String userName, String newUserName) {

	}

	@Override
	public void entityDoesntExistsByUserName(String userName) {
		if (findEntityById(userName).isEmpty())
			throw new EntityDoesntExistByUserName(
					"Entity with userName: '%s' doesn't exist".formatted(userName));
	}

	@Override
	public void entityExistsByUserName(String userName) {
		if (findEntityById(userName).isPresent()) {
			throw new EntityExistByUserName(
					"Entity with userName: '%s' already exist".formatted(userName));
		}
	}
}
