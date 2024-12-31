package dataloader;

import com.spring.dataloader.DataLoader;
import com.spring.entity.Trainee;
import com.spring.entity.Trainer;
import com.spring.entity.Training;
import com.spring.storage.Storage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.util.ReflectionTestUtils.setField;

@ExtendWith(MockitoExtension.class)
public class DataLoaderTest {

	@Mock
	private Storage storage;

	@InjectMocks
	private DataLoader dataLoader;

	private String createTempFile(String content) throws IOException {
		File tempFile = File.createTempFile("test", ".txt");
		tempFile.deleteOnExit();
		Files.writeString(Paths.get(tempFile.getAbsolutePath()), content);
		return tempFile.getAbsolutePath();
	}

	@Test
	void loadData_validFiles_loadsDataSuccessfully() throws IOException {
		String trainersContent = "Trainer,1,Susan,White,true,CARDIO\nTrainer,2,Chris,Walker,false,BODYBUILDING";
		String traineesContent = "Trainee,3,John,Doe,true,program1,2000-05-15\nTrainee,4,Jane,Smith,false,program2,1995-07-20";
		String trainingsContent = "Training,3,2,Strength Training,STRENGTH,2025-01-01T12:00:00,PT2H\nTraining,4,1,Cardio Blast,CARDIO,2024-12-31T10:00:00,PT1H30M";

		String trainersFile = createTempFile(trainersContent);
		String traineesFile = createTempFile(traineesContent);
		String trainingsFile = createTempFile(trainingsContent);

		setField(dataLoader, "trainersFilePath", trainersFile);
		setField(dataLoader, "traineesFilePath", traineesFile);
		setField(dataLoader, "trainingsFilePath", trainingsFile);

		dataLoader.loadData();

		verify(storage, times(1)).addTrainer(eq("Susan.White"), any(Trainer.class));
		verify(storage, times(1)).addTrainer(eq("Chris.Walker"), any(Trainer.class));
		verify(storage, times(1)).addTrainee(eq("John.Doe"), any(Trainee.class));
		verify(storage, times(1)).addTrainee(eq("Jane.Smith"), any(Trainee.class));
		verify(storage, times(2)).addTraining(anyString(), any(Training.class));

		Files.delete(Paths.get(trainersFile));
		Files.delete(Paths.get(traineesFile));
		Files.delete(Paths.get(trainingsFile));
	}

	@Test
	void loadData_invalidTrainerFile_throwsRuntimeException() throws IOException {
		String trainersContent = "Trainer,1,Mike,Johnson,true,INVALID_TRAINING_TYPE";
		String trainersFile = createTempFile(trainersContent);

		setField(dataLoader, "trainersFilePath", trainersFile);
		setField(dataLoader, "traineesFilePath", createTempFile(""));
		setField(dataLoader, "trainingsFilePath", createTempFile(""));

		assertThrows(IllegalArgumentException.class, () -> dataLoader.loadData());

		Files.delete(Paths.get(trainersFile));
	}

	@Test
	void loadData_invalidTraineeFile_throwsRuntimeException() throws IOException {
		String traineesContent = "Trainee,1,John,Doe,true,program1,INVALID_DATE";
		String traineesFile = createTempFile(traineesContent);

		setField(dataLoader, "trainersFilePath", createTempFile(""));
		setField(dataLoader, "traineesFilePath", traineesFile);
		setField(dataLoader, "trainingsFilePath", createTempFile(""));

		assertThrows(java.time.format.DateTimeParseException.class, () -> dataLoader.loadData());

		Files.delete(Paths.get(traineesFile));
	}

	@Test
	void loadData_invalidTrainingFile_throwsRuntimeException() throws IOException {
		String trainingsContent = "Training,1,1,Strength Training,STRENGTH,INVALID_DATE,PT1H";
		String trainingsFile = createTempFile(trainingsContent);

		setField(dataLoader, "trainersFilePath", createTempFile(""));
		setField(dataLoader, "traineesFilePath", createTempFile(""));
		setField(dataLoader, "trainingsFilePath", trainingsFile);

		assertThrows(java.time.format.DateTimeParseException.class, () -> dataLoader.loadData());

		Files.delete(Paths.get(trainingsFile));
	}

	@Test
	void readFile_validFile_returnsLines() throws IOException {
		String content = "line1\nline2\nline3";
		String testFile = createTempFile(content);

		java.util.List<String> lines = dataLoader.readFile(testFile);

		assertEquals(3, lines.size());
		assertEquals("line1", lines.get(0));
		Files.delete(Paths.get(testFile));
	}
}