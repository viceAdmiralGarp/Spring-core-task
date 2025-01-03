package repository;

import com.spring.repository.TraineeDAO;
import com.spring.service.TraineeService;
import com.spring.storage.Storage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class TraineeServiceTest {

	private static TraineeService traineeService;
	private static TraineeDAO traineeDAO;
	private static Storage storage;

	@BeforeAll
	public static void init() {
		storage = Mockito.mock(Storage.class);
		traineeDAO = new TraineeDAO(storage);
		traineeService = new TraineeService(traineeDAO);
	}

	@Test
	void trowExceptionIfTraineeByIdNotFound(){
//		Assertions.assertThrows(NullPointerException.class,() -> traineeService.findTraineeById(4L));
	}
}
