package service;

import com.spring.entity.Trainee;
import com.spring.exception.EntityNotFoundException;
import com.spring.model.TraineeDTO;
import com.spring.repository.TraineeDAO;
import com.spring.service.TraineeService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TraineeServiceTest {

	@Mock
	private TraineeDAO traineeDAO;
	@InjectMocks
	private TraineeService traineeService;

	@Test
	public void test_getAllTrainees_shouldReturnEmptyList() {
		when(traineeService.getAllTrainees()).thenReturn(Collections.emptyList());
		List<Trainee> trainees = traineeService.getAllTrainees();
		assertTrue(trainees.isEmpty());
	}

	@Test
	public void test_getAllTrainees_shouldReturnListOfTrainees() {
		List<Trainee> expectedTrainees = List.of(
				new Trainee(1L, "John", "Doe", true, "123 Main St", LocalDate.of(1990, 1, 1)),
				new Trainee(2L, "Jane", "Smith", false, "456 Elm St", LocalDate.of(1995, 2, 2))
		);
		when(traineeService.getAllTrainees()).thenReturn(expectedTrainees);
		List<Trainee> trainees = traineeService.getAllTrainees();
		assertThat(trainees).isEqualTo(expectedTrainees);
	}

	@Test
	public void test_findTraineeById_shouldThrowException_whenNotFound() {
		doThrow(new EntityNotFoundException("Trainee with ID: '1' not found")).when(traineeDAO).findEntityById(1L);
		EntityNotFoundException entityNotFoundException = assertThrows(EntityNotFoundException.class, () -> traineeService.findTraineeById(1L));
		Assertions.assertThat(entityNotFoundException.getMessage()).isEqualTo("Trainee with ID: '1' not found");
	}

	@Test
	public void test_findTraineeById_shouldReturnTrainee_whenFound() {
		Trainee expectedTrainee = new Trainee(1L, "John", "Doe", true, "123 Main St", LocalDate.of(1990, 1, 1));
		doReturn(Optional.of(expectedTrainee)).when(traineeDAO).findEntityById(1L);
		Trainee trainee = traineeService.findTraineeById(1L);
		assertThat(trainee).isEqualTo(expectedTrainee);
	}

	@Test
	public void test_updateTrainee_shouldUpdateTraineeDetails() {
		Trainee existingTrainee = new Trainee(1L, "John", "Doe", true, "123 Main St", LocalDate.of(1990, 1, 1));
		when(traineeDAO.findEntityById(1L)).thenReturn(Optional.of(existingTrainee));

		TraineeDTO updatedTraineeDTO = new TraineeDTO(0, "Jane", "Smith", false, "456 Elm St", LocalDate.of(1995, 1, 1));

		traineeService.updateTraineeById(1L, updatedTraineeDTO);

		assertThat(existingTrainee.getFirstName()).isEqualTo(updatedTraineeDTO.firstName());
		assertThat(existingTrainee.getLastName()).isEqualTo(updatedTraineeDTO.lastName());
		assertThat(existingTrainee.isActive()).isEqualTo(updatedTraineeDTO.active());
		assertThat(existingTrainee.getAddress()).isEqualTo(updatedTraineeDTO.address());
		assertThat(existingTrainee.getDateOfBirth()).isEqualTo(updatedTraineeDTO.dateOfBirth());
	}

	@Test
	public void test_deleteTraineeById_shouldRemoveTrainee() {
		Trainee traineeToDelete = new Trainee(1L, "John", "Doe", true, "123 Main St", LocalDate.of(1990, 1, 1));
		doReturn(Optional.of(traineeToDelete)).when(traineeDAO).findEntityById(1L);

		traineeService.deleteTraineeById(1L);

		verify(traineeDAO).deleteEntity(traineeToDelete);
	}
}
