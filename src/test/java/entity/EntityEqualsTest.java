package entity;

import com.spring.entity.Trainee;
import com.spring.entity.Trainer;
import com.spring.entity.Training;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static com.spring.entity.TrainingType.YOGA;
import static java.time.temporal.ChronoUnit.HOURS;
import static org.junit.jupiter.api.Assertions.*;

/**
 * This test is only needed to make sure that the @EqualsAndHashCode lombok annotation is configured correctly.
 */
class EntityEqualsTest {

	private static Trainee trainee;
	private static Trainer trainer;
	private static Training training;


	@BeforeAll
	static void init() {
		trainee = new Trainee(1,
				"Ivan",
				"Ivanov",
				"ivan4",
				"123",
				true,
				"Kiiv",
				LocalDate.of(2000, 1, 1));

		trainer = new Trainer(1,
				"Vasiliy",
				"Vasiliev",
				"vasya12",
				"456",
				false,
				YOGA);

		training = new Training(
				3,
				1,
				1,
				"Yoga",
				YOGA,
				LocalDateTime.of(
						LocalDate.of(2000, 2, 2),
						LocalTime.of(18, 30)),
				Duration.of(1, HOURS));
	}

	@Test
	void shouldValidateEqualityAndInequalityForTraineeInstances() {
		assertNotEquals(trainee, new Trainee(1,
				"Ivan",
				"Ivanov",
				"ivan4",
				"123",
				true,
				"Kiiv",
				LocalDate.of(2000, 2, 1)));

		assertEquals(trainee, new Trainee(1,
				"Ivan",
				"Ivanov",
				"ivan4",
				"123",
				true,
				"Kiiv",
				LocalDate.of(2000, 1, 1)));
	}

	@Test
	void shouldValidateEqualityAndInequalityForTrainerInstances() {
		assertNotEquals(trainer, new Trainer(2,
				"Vasiliy",
				"Vasiliev",
				"vasya12",
				"456",
				false,
				YOGA));

		assertEquals(trainer, new Trainer(1,
				"Vasiliy",
				"Vasiliev",
				"vasya12",
				"456",
				false,
				YOGA));
	}

	@Test
	void shouldValidateEqualityAndInequalityForTrainingInstances() {
		assertNotEquals(training, new Training(
				3,
				1,
				2,
				"Yoga",
				YOGA,
				LocalDateTime.of(
						LocalDate.of(2000, 2, 2),
						LocalTime.of(18, 30)),
				Duration.of(1, HOURS)));

		assertEquals(training,new Training(
				3,
				1,
				1,
				"Yoga",
				YOGA,
				LocalDateTime.of(
						LocalDate.of(2000, 2, 2),
						LocalTime.of(18, 30)),
				Duration.of(1, HOURS)));
	}

}
