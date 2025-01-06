package service;

import com.spring.entity.Trainer;
import com.spring.entity.User;
import com.spring.exception.EntityNotFoundException;
import com.spring.mapper.TrainerMapper;
import com.spring.model.TrainerDTO;
import com.spring.repository.TrainerDAO;
import com.spring.service.TrainerService;
import entity.TestUser;
import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static com.spring.entity.TrainingType.BODYBUILDING;
import static com.spring.entity.TrainingType.YOGA;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class TrainerServiceTest {

	@Mock
	private TrainerDAO trainerDAO;
	@InjectMocks
	private TrainerService trainerService;

	@Test
	void test_getAllTrainees_shouldReturnEmptyList() {
		doReturn(Collections.emptyList()).when(trainerDAO).getAll();
		List<Trainer> trainers = trainerService.getAllTrainers();
		assertThat(trainers).isEqualTo(Collections.emptyList());
	}

	@Test
	public void test_getAllTrainers_shouldReturnListOfTrainers() {
		List<Trainer> expectedTrainees = List.of(
				new Trainer(1L, "John", "Doe", true, YOGA),
				new Trainer(2L, "Jane", "Smith", false, BODYBUILDING)
		);
		doReturn(expectedTrainees).when(trainerDAO).getAll();
		List<Trainer> allTrainers = trainerService.getAllTrainers();
		assertThat(allTrainers).isEqualTo(expectedTrainees);
	}

	@Test
	public void test_findTrainerById_shouldThrowException_whenNotFound() {
		doThrow(new EntityNotFoundException("Trainer with ID: '1' not found")).when(trainerDAO).findEntityById(1L);
		EntityNotFoundException entityNotFoundException = assertThrows(EntityNotFoundException.class, () -> trainerService.findTrainerById(1L));
		assertThat(entityNotFoundException.getMessage()).isEqualTo("Trainer with ID: '1' not found");
	}

	@Test
	public void test_findTrainerById_shouldReturnTrainee_whenFound() {
		Trainer expectedTrainer = new Trainer(1L, "John", "Doe", true, YOGA);
		doReturn(Optional.of(expectedTrainer)).when(trainerDAO).findEntityById(1L);
		Trainer trainer = trainerService.findTrainerById(1L);
		AssertionsForClassTypes.assertThat(trainer).isEqualTo(expectedTrainer);
	}

	@Test
	public void test_updateTrainer_shouldUpdateTrainerDetails() {
		Long trainerId = 1L;
		Trainer existingTrainer = new Trainer(trainerId, "John", "Doe", true, YOGA);
		doReturn(Optional.of(existingTrainer)).when(trainerDAO).findEntityById(1L);

		TrainerDTO updatedTrainerDTO = new TrainerDTO(0, "Jane", "Smith", false, BODYBUILDING);

		trainerService.updateTrainerById(1L, updatedTrainerDTO);

		AssertionsForClassTypes.assertThat(existingTrainer.getFirstName()).isEqualTo(updatedTrainerDTO.firstName());
		AssertionsForClassTypes.assertThat(existingTrainer.getLastName()).isEqualTo(updatedTrainerDTO.lastName());
		AssertionsForClassTypes.assertThat(existingTrainer.isActive()).isEqualTo(updatedTrainerDTO.active());
		AssertionsForClassTypes.assertThat(existingTrainer.getTrainingType()).isEqualTo(updatedTrainerDTO.trainingType());
	}

	@Test
	public void test_deleteTrainerById_shouldRemoveTrainer() {
		Trainer trainerToDelete = new Trainer(1L, "John", "Doe", true, YOGA);
		doReturn(Optional.of(trainerToDelete)).when(trainerDAO).findEntityById(1L);

		trainerService.deleteTrainerById(1L);

		verify(trainerDAO).deleteEntity(trainerToDelete);
	}
}
