package com.spring.service;

import com.spring.entity.Trainee;
import com.spring.entity.User;
import com.spring.exception.EntityNotFoundException;
import com.spring.mapper.TraineeMapper;
import com.spring.model.TraineeDTO;
import com.spring.repository.TraineeDAO;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TraineeService {

	private final TraineeDAO traineeDAO;
	private static final Logger logger = LoggerFactory.getLogger(TraineeService.class);

	public List<Trainee> getAllTrainees() {
		List<Trainee> trainees = traineeDAO.getAll();
		logger.info("Fetched {} trainees.", trainees.size());
		return trainees;
	}

	public Trainee findTraineeById(Long id) {
		return traineeDAO.findEntityById(id)
				.orElseThrow(() -> {
					String errorMessage = "Trainee with ID: '%s' not found".formatted(id);
					logger.error(errorMessage);
					return new EntityNotFoundException(errorMessage);
				});
	}

	public void createTrainee(TraineeDTO traineeDTO) {
		Trainee entity = TraineeMapper.INSTANCE.toEntity(traineeDTO);
		entity.setUsername(User.generateUserName(entity.getFirstName(),entity.getLastName()));
		entity.setPassword(User.generatePassword());
		traineeDAO.save(entity);
		logger.info("Created trainee with ID: {}", entity.getId());
	}

	public void deleteTraineeById(Long id) {
		Trainee trainee = findTraineeById(id);
		traineeDAO.deleteEntity(trainee);
		logger.info("Deleted trainee with ID: {}", id);
	}

	public void updateTraineeById(Long id, TraineeDTO traineeDTO) {
		Trainee trainee = findTraineeById(id);

		trainee.setFirstName(traineeDTO.firstName());
		trainee.setLastName(traineeDTO.lastName());
		trainee.setActive(traineeDTO.active());
		trainee.setAddress(traineeDTO.address());
		trainee.setDateOfBirth(traineeDTO.dateOfBirth());
		logger.info("Updated trainee with ID: {}: {}", id, trainee);
	}
}
