package com.spring.service;

import com.spring.entity.Trainee;
import com.spring.entity.User;
import com.spring.exception.EntityNotFoundException;
import com.spring.mapper.TraineeMapper;
import com.spring.model.TraineeDTO;
import com.spring.repository.TraineeDAO;
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

	public Trainee findTraineeById(Long id) {
		return traineeDAO.findEntityById(id)
				.orElseThrow(() -> new EntityNotFoundException(
						"Trainee with ID: '%s' not found".formatted(id))
				);
	}

	public void createTrainee(TraineeDTO traineeDTO) {
		Trainee entity = TraineeMapper.INSTANCE.toEntity(traineeDTO);
		entity.setUsername(User.generateUserName(entity.getFirstName(),entity.getLastName()));
		entity.setPassword(User.generatePassword());
		traineeDAO.save(entity);
	}

	public void deleteTraineeById(Long id) {
		Trainee trainee = findTraineeById(id);
		traineeDAO.deleteEntityById(trainee);
	}

	public void updateTraineeById(Long id, TraineeDTO traineeDTO) {
		Trainee trainee = findTraineeById(id);

		trainee.setUserId(traineeDTO.userId());
		trainee.setFirstName(traineeDTO.firstName());
		trainee.setLastName(traineeDTO.lastName());
		trainee.setActive(traineeDTO.active());
		trainee.setAddress(traineeDTO.address());
		trainee.setDateOfBirth(traineeDTO.dateOfBirth());
	}
}
