package repository;

import com.spring.entity.Trainer;
import com.spring.repository.TrainerDAO;
import com.spring.storage.Storage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static com.spring.entity.TrainingType.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

class TrainerDAOTest {

	@Mock
	private Storage storage;

	@Spy
	@InjectMocks
	private TrainerDAO trainerDAO;

	private Map<String, Map<String, Object>> mockStorage;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		mockStorage = new HashMap<>();
		when(storage.getStorage()).thenReturn(mockStorage);
	}

	@Test
	void testGetAll_WhenNoTrainers_ShouldReturnEmptyList() {
		mockStorage.put("Trainer", new HashMap<>());

		List<Trainer> trainers = trainerDAO.getAll();
		assertTrue(trainers.isEmpty(), "Should return an empty list");
	}

	@Test
	void testGetAll_WhenTrainersExist_ShouldReturnTrainerList() {
		Map<String, Object> trainersMap = new HashMap<>();
		Trainer trainer1 = new Trainer(1L, "John", "Doe", "john_doe", "password", true, YOGA);
		Trainer trainer2 = new Trainer(2L, "Jane", "Doe", "jane_doe", "password", true, CARDIO);
		trainersMap.put("john_doe", trainer1);
		trainersMap.put("jane_doe", trainer2);
		mockStorage.put("Trainer", trainersMap);

		List<Trainer> trainers = trainerDAO.getAll();

		assertEquals(2, trainers.size(), "Should return two trainers");
		assertTrue(trainers.contains(trainer1), "Should contain trainer1");
		assertTrue(trainers.contains(trainer2), "Should contain trainer2");
	}

	@Test
	void testFindEntityById_WhenTrainerExists_ShouldReturnTrainer() {
		Map<String, Object> trainersMap = new HashMap<>();
		Trainer trainer = new Trainer(1L, "John", "Doe", "john_doe", "password", true, YOGA);
		trainersMap.put("john_doe", trainer);
		mockStorage.put("Trainer", trainersMap);

		Optional<Trainer> foundTrainer = trainerDAO.findEntityById(1L);

		assertTrue(foundTrainer.isPresent(), "Trainer should be found");
		assertEquals(trainer, foundTrainer.get(), "Returned trainer should match the expected trainer");
	}

	@Test
	void testFindEntityById_WhenTrainerNotFound_ShouldReturnEmpty() {
		mockStorage.put("Trainer", new HashMap<>());

		Optional<Trainer> foundTrainer = trainerDAO.findEntityById(1L);

		assertFalse(foundTrainer.isPresent(), "Trainer should not be found");
	}

	@Test
	void testSave_WhenNewTrainer_ShouldSaveTrainer() {
		Map<String, Object> trainersMap = new HashMap<>();
		mockStorage.put("Trainer", trainersMap);

		Trainer trainer = new Trainer(1L, "John", "Doe", "john_doe", "password", true, YOGA);

		when(trainerDAO.save("john_doe", trainer)).thenReturn(trainer);
		Trainer savedTrainer = trainerDAO.save("john_doe", trainer);

		assertEquals(trainer, savedTrainer, "Saved trainer should match the input trainer");
		assertTrue(trainersMap.containsKey("john_doe"), "Trainer should be saved in the storage");
	}

	@Test
	void testDeleteEntityById_WhenTrainerExists_ShouldDeleteTrainer() {
		Map<String, Object> trainersMap = new HashMap<>();
		Trainer trainer = new Trainer(1L, "John", "Doe", "john_doe", "password", true, YOGA);
		trainersMap.put("john_doe", trainer);
		mockStorage.put("Trainer", trainersMap);

		trainerDAO.deleteEntityById(1L);

		assertFalse(trainersMap.containsKey("john_doe"), "Trainer should be deleted from the storage");
	}

	@Test
	void testDeleteEntityById_WhenTrainerNotFound_ShouldThrowException() {
		mockStorage.put("Trainer", new HashMap<>());

		IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
			trainerDAO.deleteEntityById(1L);
		});

		assertEquals("Trainer with ID 1 was not found", thrown.getMessage(), "Exception message should match");
	}
}
