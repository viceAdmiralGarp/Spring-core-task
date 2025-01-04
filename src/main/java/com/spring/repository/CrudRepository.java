package com.spring.repository;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.List;
import java.util.Optional;

public interface CrudRepository<E, ID> {

	List<E> getAll();

	Optional<E> findEntityById(ID id);

	void save(E entity);

	void deleteEntityById(E entity);


}
