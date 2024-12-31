package dataloader;

import com.spring.dataloader.DataLoader;
import com.spring.entity.Trainee;
import com.spring.entity.Trainer;
import com.spring.entity.Training;
import com.spring.entity.TrainingType;
import com.spring.storage.Storage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.test.util.ReflectionTestUtils;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;


class DataLoaderTest {

	@InjectMocks
	@Spy
	private DataLoader dataLoader;

	@Mock
	private Storage storage;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);

		ReflectionTestUtils.setField(dataLoader, "trainersFilePath", "src/test/resources/trainers.csv");
		ReflectionTestUtils.setField(dataLoader, "traineesFilePath", "src/test/resources/trainees.csv");
		ReflectionTestUtils.setField(dataLoader, "trainingsFilePath", "src/test/resources/trainings.csv");
	}

	@Test
	void testLoadDataSuccess() throws IOException {
		String trainersData = "Trainer,101,Mike,Johnson,Mike.Johnson,kVHkib2AQG,true,YOGA";
		String traineesData = "Trainee,1,John,Doe,John.Doe,6LUq7lM5nW,true,123 Elm Street,2000-05-15";
		String trainingsData = "Training,1,101,Morning Yoga,YOGA,2024-12-30T08:00:00,PT1H";

		doReturn(List.of(trainersData)).when(dataLoader).readFile("src/test/resources/trainers.csv");
		doReturn(List.of(traineesData)).when(dataLoader).readFile("src/test/resources/trainees.csv");
		doReturn(List.of(trainingsData)).when(dataLoader).readFile("src/test/resources/trainings.csv");

		dataLoader.loadData();

		verify(storage, times(1)).addTrainer(eq("Mike.Johnson"), any(Trainer.class));
		verify(storage, times(1)).addTrainee(eq("John.Doe"), any(Trainee.class));
		verify(storage, times(1)).addTraining(eq("Morning Yoga"), any(Training.class));
	}

	@Test
	void testLoadDataIOException() throws IOException {
		doThrow(new IOException("File not found")).when(dataLoader).readFile(anyString());

		RuntimeException exception = assertThrows(RuntimeException.class, dataLoader::loadData);
		assertEquals("Failed to initialize data from files", exception.getMessage());
	}

	@Test
	void testParseTrainer() {
		String[] trainerParts = {"Trainer", "101", "Mike", "Johnson", "Mike.Johnson", "kVHkib2AQG", "true", "YOGA"};
		Trainer trainer = ReflectionTestUtils.invokeMethod(dataLoader, "parseTrainer", (Object) trainerParts);

		assertNotNull(trainer);
		assertEquals(101L, trainer.getUserId());
		assertEquals("Mike.Johnson", trainer.getUsername());
		assertEquals(TrainingType.YOGA, trainer.getTrainingType());
	}

	@Test
	void testParseTrainee() {
		String[] traineeParts = {"Trainee", "1", "John", "Doe", "John.Doe", "6LUq7lM5nW", "true", "123 Elm Street", "2000-05-15"};
		Trainee trainee = ReflectionTestUtils.invokeMethod(dataLoader, "parseTrainee", (Object) traineeParts);

		assertNotNull(trainee);
		assertEquals(1L, trainee.getUserId());
		assertEquals("John.Doe", trainee.getUsername());
		assertEquals("123 Elm Street", trainee.getAddress());
	}

	@Test
	void testParseTraining() {
		String[] trainingParts = {"Training", "1", "101", "Morning Yoga", "YOGA", "2024-12-30T08:00:00", "PT1H"};
		Training training = ReflectionTestUtils.invokeMethod(dataLoader, "parseTraining", (Object) trainingParts);

		assertNotNull(training);
		assertEquals(1L, training.getTraineeId());
		assertEquals("Morning Yoga", training.getTrainingName());
		assertEquals(TrainingType.YOGA, training.getTrainingType());
	}
}