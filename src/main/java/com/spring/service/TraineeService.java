package com.spring.service;

import com.spring.entity.Trainee;
import com.spring.entity.Trainer;
import com.spring.model.TraineeDTO;
import com.spring.model.TrainerDTO;
import com.spring.repository.TraineeDAO;
import com.spring.utils.EntityUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TraineeService {

	private final TraineeDAO traineeDAO;

	public List<Trainee> getAllTrainees() {
		return traineeDAO.getAll();
	}

	public Trainee findTrainerById(Long id) {
		return traineeDAO.findEntityById(id)
				.orElseThrow(() -> new IllegalArgumentException(
						"Trainee with ID: '%s' not found".formatted(id))
				);
	}

	public void createTrainee(TraineeDTO traineeDTO) {
		String userName = EntityUtils.generateUserName(traineeDTO.firstName(), traineeDTO.lastName());
		String password = EntityUtils.generatePassword();
		Trainee trainee = new Trainee(traineeDTO.userId(), traineeDTO.firstName(), traineeDTO.lastName(), userName, password, traineeDTO.isActive(),
				traineeDTO.address(), traineeDTO.dateOfBirth());
		traineeDAO.save(userName, trainee);
	}

	public void deleteTraineeById(Long id) {
		traineeDAO.deleteEntityById(id);
	}

	public void updateTraineeById(Long id, TraineeDTO traineeDTO) {
		Trainee trainee = findTrainerById(id);
		String userName = EntityUtils.generateUserName(traineeDTO.firstName(), traineeDTO.lastName());

		trainee.setUserId(traineeDTO.userId());
		trainee.setFirstName(traineeDTO.firstName());
		trainee.setLastName(traineeDTO.lastName());
		trainee.setUsername(userName);
		trainee.setActive(traineeDTO.isActive());
		trainee.setAddress(traineeDTO.address());
		trainee.setDateOfBirth(traineeDTO.dateOfBirth());
	}

}
