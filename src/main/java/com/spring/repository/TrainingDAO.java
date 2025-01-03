package com.spring.repository;

import com.spring.entity.Training;
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
public class TrainingDAO implements CrudRepository<Training, Long> {

	private final Storage storage;


	@Override
	public List<Training> getAll() {
		return List.of();
	}

	@Override
	public Optional<Training> findEntityById(Long aLong) {
		return Optional.empty();
	}

	@Override
	public void save(Training entity) {

	}

	@Override
	public void deleteEntityById(Long aLong) {

	}
}
