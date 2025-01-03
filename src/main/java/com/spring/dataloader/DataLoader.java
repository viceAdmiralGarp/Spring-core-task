package com.spring.dataloader;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.spring.entity.Entity;
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
import java.util.ArrayList;
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

		dataContainer.getEntities().forEach(entity -> storage.addEntity(entity.getClass(), entity.getId(), entity));
	}
}
