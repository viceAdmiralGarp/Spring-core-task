package com.spring.storage;

import com.spring.entity.Entity;
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

	private final Map<Class<?>, Map<Object, Object>> storage = new HashMap<>();

	public <T extends Entity<?>> void addEntity(Class<? extends T> typeToken, Object id, T entity) {
		storage.computeIfAbsent(typeToken, k -> new HashMap<>()).put(id, entity);
	}

	public <T> List<T> getEntities(Class<T> typeToken) {
		return storage.get(typeToken).values()
				.stream()
				.map(typeToken::cast)
				.toList();
	}
}
