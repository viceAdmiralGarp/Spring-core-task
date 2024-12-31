package com.spring.service;

import com.spring.entity.Trainer;
import com.spring.model.TrainerDTO;
import com.spring.repository.TrainerDAO;
import com.spring.mapper.trainer.TrainerMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.function.Consumer;

@Service
@Validated
@RequiredArgsConstructor
public class TrainerService {

	private final TrainerDAO trainerDAO;

	public List<Trainer> getAllTrainers() {
		return trainerDAO.getAll();
	}

	public Trainer findByTrainerByUsername( String userName) {
		return trainerDAO.findEntityById(userName)
				.orElseThrow(() -> new NullPointerException(
						"Trainer with userName: '%s' not found".formatted(userName))
				);
	}

	public Trainer createTrainer(TrainerDTO trainer) {
		trainerDAO.entityExistsByUserName(trainer.username());
		Trainer entity = TrainerMapper.toEntity(trainer);
		trainerDAO.save(entity);
		return entity;
	}

	public void deleteTrainerByUserName(String userName) {
		trainerDAO.entityDoesntExistsByUserName(userName);
		trainerDAO.deleteEntityByUserName(userName);
	}

	public void updateTrainerUserNameByUserName(String userName, String newUserName) {
		trainerDAO.entityDoesntExistsByUserName(userName);
		trainerDAO.entityExistsByUserName(newUserName);
		trainerDAO.updateUserName(userName,newUserName);
	}

	public void updateTrainerFirstNameByUserName(String userName, String firstName) {
		updateFieldByUserName(userName,trainer -> trainer.setFirstName(firstName));
	}

	public void updateTrainerLastNameByUserName(String userName, String lastName) {
		updateFieldByUserName(userName,trainer -> trainer.setLastName(lastName));
	}

	public void updateTrainerPasswordByUserName(String userName, String password) {
		updateFieldByUserName(userName, trainer -> trainer.setPassword(password));
	}

	public void updateTrainerActiveStatusByUserName(String userName, boolean isActive) {
		updateFieldByUserName(userName, trainer -> trainer.setActive(isActive));
	}

	public void updateFieldByUserName(String userName, Consumer<Trainer> updateFunction) {
		Trainer trainer = trainerDAO.findEntityById(userName)
				.orElseThrow(() -> new NullPointerException(
						"Trainer with userName: '%s' not found".formatted(userName))
				);
		updateFunction.accept(trainer);
	}
}
