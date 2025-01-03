package com.spring.dataloader;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.spring.storage.Storage;
import jakarta.annotation.PostConstruct;
import lombok.Data;
import lombok.RequiredArgsConstructor;
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

	@PostConstruct
	private void loadData() throws IOException {

		objectMapper.registerModule(new JavaTimeModule());

		File file = new File(init);
		DataContainer dataContainer = objectMapper.readValue(file, DataContainer.class);

		dataContainer.getEntities().forEach(entity -> storage.addEntity(entity.getId(), entity));
	}
}
