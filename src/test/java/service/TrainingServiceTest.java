package service;

import com.spring.entity.Training;
import com.spring.entity.TrainingType;
import com.spring.exception.EntityNotFoundException;
import com.spring.model.TrainingDTO;
import com.spring.repository.TrainingDAO;
import com.spring.service.TrainingService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class TrainingServiceTest {

	@Mock
	private TrainingDAO trainingDAO;
	@InjectMocks
	private TrainingService trainingService;

	@Test
	void test_getAllTrainings_shouldReturnEmptyList() {
		doReturn(Collections.emptyList()).when(trainingDAO).getAll();
		List<Training> trainings = trainingService.getAllTrainings();
		assertThat(trainings).isEqualTo(Collections.emptyList());
	}

	@Test
	public void test_getAllTrainings_shouldReturnListOfTrainings() {
		List<Training> expectedTrainings = List.of(
				new Training(1L, 101L, 201L, "Yoga", TrainingType.YOGA, LocalDateTime.now(), Duration.ofMinutes(60)),
				new Training(2L, 102L, 202L, "Bodybuilding", TrainingType.BODYBUILDING, LocalDateTime.now(), Duration.ofMinutes(90))
		);

		doReturn(expectedTrainings).when(trainingDAO).getAll();
		List<Training> allTrainings = trainingService.getAllTrainings();
		assertThat(allTrainings).isEqualTo(expectedTrainings);
	}

	@Test
	public void test_findTrainingById_shouldThrowException_whenNotFound() {
		doThrow(new EntityNotFoundException("Training with ID: '1' not found")).when(trainingDAO).findEntityById(1L);
		EntityNotFoundException entityNotFoundException = assertThrows(EntityNotFoundException.class, () -> trainingService.findTrainingById(1L));
		assertThat(entityNotFoundException.getMessage()).isEqualTo("Training with ID: '1' not found");
	}

	@Test
	public void test_findTrainingById_shouldReturnTraining_whenFound() {
		Training expectedTraining = new Training(1L, 101L, 201L, "Yoga", TrainingType.YOGA, LocalDateTime.now(), Duration.ofMinutes(60));

		doReturn(Optional.of(expectedTraining)).when(trainingDAO).findEntityById(1L);
		Training training = trainingService.findTrainingById(1L);
		assertThat(training).isEqualTo(expectedTraining);
	}

	@Test
	public void test_createTraining_shouldSaveTraining() {
		TrainingDTO trainingDTO = new TrainingDTO(0L, 101L, 201L, "Yoga", TrainingType.YOGA, LocalDateTime.now(), Duration.ofMinutes(60));
		Training expectedTraining = new Training(0L, 101L, 201L, "Yoga", TrainingType.YOGA, trainingDTO.date(), trainingDTO.duration());
		trainingService.createTraining(trainingDTO);
		verify(trainingDAO).save(expectedTraining);
	}


	@Test
	public void test_deleteTrainingById_shouldRemoveTraining() {
		Training trainingToDelete = new Training(1L, 101L, 201L, "Yoga", TrainingType.YOGA, LocalDateTime.now(), Duration.ofMinutes(60));
		doReturn(Optional.of(trainingToDelete)).when(trainingDAO).findEntityById(1L);
		trainingService.deleteTrainingById(1L);
		verify(trainingDAO).deleteEntity(trainingToDelete);
	}

	@Test
	public void test_updateTrainingById_shouldUpdateTrainingDetails() {
		Training existingTraining = new Training(1L, 101L, 201L, "Yoga", TrainingType.YOGA, LocalDateTime.now(), Duration.ofMinutes(60));
		doReturn(Optional.of(existingTraining)).when(trainingDAO).findEntityById(1L);
		TrainingDTO updatedTrainingDTO = new TrainingDTO(1L, 102L, 202L, "Bodybuilding", TrainingType.BODYBUILDING, LocalDateTime.now(), Duration.ofMinutes(90));
		trainingService.updateTrainingById(1L, updatedTrainingDTO);

		assertThat(existingTraining.getTrainingName()).isEqualTo(updatedTrainingDTO.trainingName());
		assertThat(existingTraining.getDuration()).isEqualTo(updatedTrainingDTO.duration());
		assertThat(existingTraining.getDate()).isEqualTo(updatedTrainingDTO.date());
		assertThat(existingTraining.getTrainerId()).isEqualTo(updatedTrainingDTO.trainerId());
		assertThat(existingTraining.getTraineeId()).isEqualTo(updatedTrainingDTO.traineeId());
	}
}
