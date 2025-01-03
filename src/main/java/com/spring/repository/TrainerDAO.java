package com.spring.repository;

import com.spring.entity.Trainer;
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
public class TrainerDAO implements CrudRepository<Trainer, Long> {

	private final Storage storage;

	@Override
	public List<Trainer> getAll() {
		return List.of();
	}

	@Override
	public Optional<Trainer> findEntityById(Long aLong) {
		return Optional.empty();
	}

	@Override
	public void save(Trainer entity) {
	}

	@Override
	public void deleteEntityById(Long aLong) {

	}
}
