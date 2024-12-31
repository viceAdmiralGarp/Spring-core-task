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
public class TrainerDAO implements CrudRepository<Trainer, Long> {

	private final Storage storage;
	private static final String KEY = "Trainer";

	@Override
	public List<Trainer> getAll() {
		return storage.getStorage()
				.getOrDefault(KEY, Map.of())
				.values()
				.stream()
				.filter(Trainer.class::isInstance)
				.map(Trainer.class::cast)
				.collect(toList());
	}

	@Override
	public Optional<Trainer> findEntityById(Long id) {
		return storage.getStorage()
				.getOrDefault(KEY, Map.of())
				.values()
				.stream()
				.filter(Trainer.class::isInstance)
				.map(Trainer.class::cast)
				.filter(trainer -> trainer.getUserId() == id)
				.findFirst();
	}


	@Override
	public Trainer save(Trainer entity) {
		return null;
	}

	@Override
	public void deleteEntityById(Long aLong) {

	}

}
