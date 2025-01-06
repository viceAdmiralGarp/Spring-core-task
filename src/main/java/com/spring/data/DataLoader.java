package com.spring.data;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.spring.storage.Storage;
import jakarta.annotation.PostConstruct;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;

@Data
@Component
@RequiredArgsConstructor
public class DataLoader {

	@Value("${storage.init.file}")
	private String init;

	private final Storage storage;
	private final ObjectMapper objectMapper = new ObjectMapper();
	private static final Logger logger = LoggerFactory.getLogger(DataLoader.class);

	@PostConstruct
	public void loadData() throws IOException {
		logger.info("Starting to load data from file: {}", init);

		File file = new File(init);
		if (!file.exists()) {
			logger.error("File not found: {}", init);
			throw new IOException("File not found: " + init);
		}

		objectMapper.registerModule(new JavaTimeModule());
		logger.debug("ObjectMapper initialized with JavaTimeModule.");

		try {
			DataContainer dataContainer = objectMapper.readValue(file, DataContainer.class);
			logger.info("Successfully loaded data from file: {}", init);

			dataContainer.getEntities().forEach(entity -> {
				logger.debug("Adding entity with ID: {}", entity.getId());
				storage.addEntity(entity.getId(), entity);
			});

			logger.info("Data loading completed, {} entities added.", dataContainer.getEntities().size());
		} catch (IOException e) {
			logger.error("Error while loading data from file: {}", init, e);
			throw e;
		}
	}
}
