package com.spring.service;

import com.spring.entity.Trainer;
import com.spring.entity.User;
import com.spring.mapper.TrainerMapper;
import com.spring.model.TrainerDTO;
import com.spring.repository.TrainerDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Service
@Validated
@RequiredArgsConstructor
public class TrainerService {

	private final TrainerDAO trainerDAO;

	public List<Trainer> getAllTrainers() {
		return trainerDAO.getAll();
	}

	public Trainer findTrainerById(Long id) {
		return trainerDAO.findEntityById(id)
				.orElseThrow(() -> new NullPointerException(
						"Trainer with ID: '%s' not found".formatted(id))
				);
	}

	public void createTrainer(TrainerDTO trainerDTO) {
		Trainer entity = TrainerMapper.INSTANCE.toEntity(trainerDTO);
		entity.setUsername(User.generateUserName(entity.getFirstName(),entity.getLastName()));
		entity.setPassword(User.generatePassword());
		trainerDAO.save(entity);
	}

	public void deleteTrainerById(Long id) {
		Trainer trainer = findTrainerById(id);
		trainerDAO.deleteEntityById(trainer);
	}

	public void updateTrainerById(Long id, TrainerDTO trainerDTO) {
		Trainer trainer = findTrainerById(id);

		trainer.setUserId(trainerDTO.userId());
		trainer.setFirstName(trainerDTO.firstName());
		trainer.setLastName(trainerDTO.lastName());
		trainer.setActive(trainerDTO.active());
		trainer.setTrainingType(trainerDTO.trainingType());
	}
}