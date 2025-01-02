package com.spring.dataloader;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.spring.entity.Trainee;
import com.spring.entity.Trainer;
import com.spring.entity.Training;
import com.spring.storage.Storage;
import jakarta.annotation.PostConstruct;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Data
@Component
@RequiredArgsConstructor
public class DataLoader {

	@Value("${storage.init.file}")
	private String init;

	private final Storage storage;
	private final ObjectMapper objectMapper = new ObjectMapper();

	@PostConstruct
	private void loadData() throws IOException {

		objectMapper.registerModule(new JavaTimeModule());

		File file = new File(init);
		DataContainer dataContainer = objectMapper.readValue(file, DataContainer.class);

		addEntitiesToStorage(dataContainer.getTrainers(), Trainer.class);
		addEntitiesToStorage(dataContainer.getTrainees(), Trainee.class);
		addEntitiesToStorage(dataContainer.getTraining(), Training.class);
	}

	private <T> void addEntitiesToStorage(List<T> entities, Class<T> entityClass) {
		if (entities != null && !entities.isEmpty()) {
			for (T entity : entities) {
				String key = determineKey(entity);
				storage.addEntity(entityClass, key, entity);
			}
		}
	}

	private <T> String determineKey(T entity) {
		if (entity instanceof Trainer) {
			return ((Trainer) entity).getUsername();
		} else if (entity instanceof Trainee) {
			return ((Trainee) entity).getUsername();
		} else if (entity instanceof Training) {
			return String.valueOf(((Training) entity).getTrainingId());
		}
		throw new IllegalArgumentException("Unknown entity type: " + entity.getClass());
	}

	@Data
	public static class DataContainer {
		private List<Training> training;
		private List<Trainer> trainers;
		private List<Trainee> trainees;
	}
}
