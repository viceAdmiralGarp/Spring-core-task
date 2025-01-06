package com.spring.storage;

import com.spring.entity.Entity;
import com.spring.entity.User;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
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

	public <T extends Entity<?>> void addEntity(Object id, T entity) {
		storage.computeIfAbsent(entity.getClass(), k -> new HashMap<>()).put(id, entity);
	}

	public <T> List<T> getEntities(Class<T> typeToken) {
		return storage.getOrDefault(typeToken, Collections.emptyMap())
				.values()
				.stream()
				.map(typeToken::cast)
				.toList();
	}

}
