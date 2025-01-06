package com.spring.storage;

import com.spring.entity.Entity;
import com.spring.entity.User;
import com.spring.service.TrainingService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@Component
@RequiredArgsConstructor
public class Storage {

	private final Map<Class<?>, Map<Object, Object>> storage = new HashMap<>();
	private static final Logger logger = LoggerFactory.getLogger(Storage.class);

	public <T extends Entity<?>> void addEntity(Object id, T entity) {
		logger.info("Adding entity of type {} with ID: {}", entity.getClass().getSimpleName(), id);
		storage.computeIfAbsent(entity.getClass(), k -> new HashMap<>()).put(id, entity);
		logger.info("Entity with ID: {} added to the storage.", id);
	}

	public <T> List<T> getEntities(Class<T> typeToken) {
		logger.info("Retrieving entities of type {}", typeToken.getSimpleName());
		List<T> entities = storage.getOrDefault(typeToken, Collections.emptyMap())
				.values()
				.stream()
				.map(typeToken::cast)
				.toList();

		logger.info("Retrieved {} entities of type {}", entities.size(), typeToken.getSimpleName());
		return entities;
	}

}
