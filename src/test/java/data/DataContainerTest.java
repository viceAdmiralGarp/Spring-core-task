package data;

import com.spring.data.DataContainer;
import com.spring.entity.Trainee;
import com.spring.entity.Trainer;
import com.spring.entity.Training;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DataContainerTest {

	private DataContainer dataContainer;

	@BeforeEach
	public void setUp() {
		dataContainer = new DataContainer();
	}

	@Test
	public void testSetTraining() {
		List<Training> trainings = List.of(new Training(), new Training());

		dataContainer.setTraining(trainings);

		assertEquals(2, dataContainer.getEntities().size(), "The entities list should contain 2 trainings.");

		assertTrue(dataContainer.getEntities().get(0) instanceof Training, "The first entity should be of type Training.");
		assertTrue(dataContainer.getEntities().get(1) instanceof Training, "The second entity should be of type Training.");
	}

	@Test
	public void testSetTrainers() {
		List<Trainer> trainers = List.of(new Trainer(), new Trainer());

		dataContainer.setTrainers(trainers);

		assertEquals(2, dataContainer.getEntities().size(), "The entities list should contain 2 trainers.");

		assertTrue(dataContainer.getEntities().get(0) instanceof Trainer, "The first entity should be of type Trainer.");
		assertTrue(dataContainer.getEntities().get(1) instanceof Trainer, "The second entity should be of type Trainer.");
	}

	@Test
	public void testSetTrainees() {
		List<Trainee> trainees = List.of(new Trainee(), new Trainee());

		dataContainer.setTrainees(trainees);

		assertEquals(2, dataContainer.getEntities().size(), "The entities list should contain 2 trainees.");

		assertTrue(dataContainer.getEntities().get(0) instanceof Trainee, "The first entity should be of type Trainee.");
		assertTrue(dataContainer.getEntities().get(1) instanceof Trainee, "The second entity should be of type Trainee.");
	}

	@Test
	public void testSetAllEntities() {
		List<Training> trainings = List.of(new Training());
		List<Trainer> trainers = List.of(new Trainer());
		List<Trainee> trainees = List.of(new Trainee());

		dataContainer.setTraining(trainings);
		dataContainer.setTrainers(trainers);
		dataContainer.setTrainees(trainees);

		assertEquals(3, dataContainer.getEntities().size(), "The entities list should contain 3 items.");

		assertTrue(dataContainer.getEntities().get(0) instanceof Training, "The first entity should be of type Training.");
		assertTrue(dataContainer.getEntities().get(1) instanceof Trainer, "The second entity should be of type Trainer.");
		assertTrue(dataContainer.getEntities().get(2) instanceof Trainee, "The third entity should be of type Trainee.");
	}
}
