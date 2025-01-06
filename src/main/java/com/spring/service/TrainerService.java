package com.spring.service;

import com.spring.entity.Trainer;
import com.spring.entity.User;
import com.spring.exception.EntityNotFoundException;
import com.spring.mapper.TrainerMapper;
import com.spring.model.TrainerDTO;
import com.spring.repository.TraineeDAO;
import com.spring.repository.TrainerDAO;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TrainerService {

	private final TrainerDAO trainerDAO;
	private static final Logger logger = LoggerFactory.getLogger(TrainerService.class);

	public List<Trainer> getAllTrainers() {
		List<Trainer> trainers = trainerDAO.getAll();
		logger.info("Fetched {} trainers.", trainers.size());
		return trainers;
	}

	public Trainer findTrainerById(Long id) {
		return trainerDAO.findEntityById(id)
				.orElseThrow(() -> {
					String errorMessage = "Trainer with ID: '%s' not found".formatted(id);
					logger.error(errorMessage);
					return new EntityNotFoundException(errorMessage);
				});
	}

	public void createTrainer(TrainerDTO trainerDTO) {
		Trainer entity = TrainerMapper.INSTANCE.toEntity(trainerDTO);
		entity.setUsername(User.generateUserName(entity.getFirstName(),entity.getLastName()));
		entity.setPassword(User.generatePassword());
		trainerDAO.save(entity);
		logger.info("Created trainer with ID: {}", entity.getId());
	}

	public void deleteTrainerById(Long id) {
		Trainer trainer = findTrainerById(id);
		trainerDAO.deleteEntity(trainer);
		logger.info("Deleted trainer with ID: {}", id);
	}

	public void updateTrainerById(Long id, TrainerDTO trainerDTO) {
		Trainer trainer = findTrainerById(id);

		trainer.setFirstName(trainerDTO.firstName());
		trainer.setLastName(trainerDTO.lastName());
		trainer.setUsername(User.generateUserName(trainerDTO.firstName(),trainerDTO.lastName()));
		trainer.setActive(trainerDTO.active());
		trainer.setTrainingType(trainerDTO.trainingType());
		logger.info("Updated trainer with ID: {}: {}", id, trainer);
	}
}