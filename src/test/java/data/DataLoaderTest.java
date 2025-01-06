package data;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.spring.data.DataLoader;
import com.spring.entity.Training;
import com.spring.storage.Storage;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.io.TempDir;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


@ExtendWith(MockitoExtension.class)
public class DataLoaderTest {

	private DataLoader dataLoader;
	private ObjectMapper objectMapper;
	@TempDir
	Path tempDir;

	@BeforeEach
	public void setup() {
		objectMapper = new ObjectMapper();
		objectMapper.registerModule(new JavaTimeModule());
		dataLoader = new DataLoader(new Storage());
	}

	@Test
	public void testLoadData_success() throws IOException {
		File tempFile = tempDir.resolve("init-test.json").toFile();
		Files.writeString(tempFile.toPath(), jsonData);

		ReflectionTestUtils.setField(dataLoader, "init", tempFile.getAbsolutePath());

		dataLoader.loadData();

		List<Training> loadedTrainings = dataLoader.getStorage().getEntities(Training.class);

		assertNotNull(loadedTrainings);
		assertEquals(2, loadedTrainings.size());
	}

	@Test
	public void testLoadData_fileNotFound() {
		Assertions.assertThatThrownBy(() -> dataLoader.loadData())
				.isInstanceOf(NullPointerException.class);
	}

	private final static String jsonData = """
				{
				  "training": [
				    {
				      "id": 1,
				      "traineeId": 101,
				      "trainerId": 201,
				      "trainingName": "Yoga",
				      "trainingType": "YOGA",
				      "date": "2025-01-10T09:00:00",
				      "duration": "PT2H"
				    },
				    {
				      "id": 2,
				      "traineeId": 102,
				      "trainerId": 202,
				      "trainingName": "Body",
				      "trainingType": "BODYBUILDING",
				      "date": "2025-01-15T10:00:00",
				      "duration": "PT3H"
				    }
				  ],
				  "trainers": [
				    {
				      "userId": 201,
				      "firstName": "Alice",
				      "lastName": "Smith",
				      "username": "Alice.Smith",
				      "password": "asdasd[564",
				      "active": true,
				      "trainingType": "BODYBUILDING"
				    },
				    {
				      "userId": 202,
				      "firstName": "Bob",
				      "lastName": "Johnson",
				      "username": "Bob.Johnson",
				      "password": "asjfasofpa[564",
				      "active": true,
				      "trainingType": "YOGA"
				    }
				  ],
				  "trainees": [
				    {
				      "userId": 101,
				      "firstName": "John",
				      "lastName": "Doe",
				      "username": "John.Doe",
				      "password": "hasdk_sdfa12365",
				      "active": true,
				      "address": "123 Main St, Springfield",
				      "dateOfBirth": "1990-05-15"
				    },
				    {
				      "userId": 102,
				      "firstName": "Jane",
				      "lastName": "Davis",
				      "username": "Jane.Davis",
				      "password": "alkisdfaisod_sadjas45",
				      "active": true,
				      "address": "456 Elm St, Riverside",
				      "dateOfBirth": "1988-07-22"
				    }
				  ]
				}
        """;
}