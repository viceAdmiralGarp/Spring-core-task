package com.spring.storage;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@Component
@RequiredArgsConstructor
public class Storage {

	private final Map<Class<?>, Map<String, Object>> storage = new HashMap<>();

	public <T> void addEntity(Class<T> typeToken, String username, T entity) {
		storage.computeIfAbsent(typeToken, k -> new HashMap<>()).put(username, entity);
	}

	public <T> List<T> getEntities(Class<T> typeToken) {
		return storage.get(typeToken).values()
				.stream()
				.map(typeToken::cast)
				.toList();
	}
}
