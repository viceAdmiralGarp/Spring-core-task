package storage;

import com.spring.entity.Trainee;
import com.spring.entity.Trainer;
import com.spring.entity.Training;
import com.spring.entity.TrainingType;
import com.spring.storage.Storage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
public class StorageTest {

	@InjectMocks
	private Storage storage;

	@Test
	void addTrainer_addsTrainerToStorage() {
		Trainer trainer = new Trainer(1L, "firstName", "lastName", "username", "password", true, TrainingType.CARDIO);
		storage.addTrainer("username", trainer);
		Map<String, Object> trainers = storage.getStorage().get("Trainer");
		assertNotNull(trainers);
		assertEquals(trainer, trainers.get("username"));
	}

	@Test
	void addTrainee_addsTraineeToStorage() {
		Trainee trainee = new Trainee(1L, "firstName", "lastName", "username", "password", true, "program", LocalDate.now());
		storage.addTrainee("username", trainee);
		Map<String, Object> trainees = storage.getStorage().get("Trainee");
		assertNotNull(trainees);
		assertEquals(trainee, trainees.get("username"));
	}

	@Test
	void addTraining_addsTrainingToStorage() {
		Training training = new Training(3L, 1L, 2L, "trainingName", TrainingType.CARDIO, LocalDateTime.now(), Duration.ofHours(1));
		storage.addTraining("trainingName", training);
		Map<String, Object> trainings = storage.getStorage().get("Training");
		assertNotNull(trainings);
		assertEquals(training, trainings.get("trainingName"));
	}
}