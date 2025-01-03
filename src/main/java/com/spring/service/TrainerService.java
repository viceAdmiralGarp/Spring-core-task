package com.spring.service;

import com.spring.entity.Trainer;
import com.spring.model.TrainerDTO;
import com.spring.repository.TrainerDAO;
import com.spring.utils.EntityUtils;
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

//	public Trainer findTrainerById(Long id) {
//		return trainerDAO.findEntityById(id)
//				.orElseThrow(() -> new NullPointerException(
//						"Trainer with ID: '%s' not found".formatted(id))
//				);
//	}
//
//	public void createTrainer(TrainerDTO trainerDTO) {
//		String userName = EntityUtils.generateUserName(trainerDTO.firstName(), trainerDTO.lastName());
//		String password = EntityUtils.generatePassword();
//		Trainer trainer = new Trainer(trainerDTO.userId(), trainerDTO.firstName(), trainerDTO.lastName(), userName, password, trainerDTO.isActive(), trainerDTO.trainingType());
//		trainerDAO.save(userName, trainer);
//	}
//
//	public void deleteTrainerById(Long id) {
//		trainerDAO.deleteEntityById(id);
//	}
//
//	public void updateTrainerById(Long id, TrainerDTO trainerDTO) {
//		Trainer trainer = findTrainerById(id);
//		String userName = EntityUtils.generateUserName(trainerDTO.firstName(), trainerDTO.lastName());
//
//		trainer.setUserId(trainerDTO.userId());
//		trainer.setFirstName(trainerDTO.firstName());
//		trainer.setLastName(trainerDTO.lastName());
//		trainer.setUsername(userName);
//		trainer.setActive(trainerDTO.isActive());
//		trainer.setTrainingType(trainerDTO.trainingType());
//	}

}
