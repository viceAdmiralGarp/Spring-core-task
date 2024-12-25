package service;

import entity.Trainer;
import lombok.RequiredArgsConstructor;
import mapper.trainer.TrainerMapper;
import model.TrainerDTO;
import org.springframework.stereotype.Service;
import repository.TrainerDAO;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TrainerService {

	private final TrainerDAO trainerDAO;

	public List<Trainer> getAllTrainers() {
		return trainerDAO.getAll();
	}

	public Trainer findByTrainerByUsername(String userName) {
		return trainerDAO.findEntityByUserName(userName)
				.orElseThrow(() -> new NullPointerException(
						"Trainer with userName: '%s' not found".formatted(userName))
				);
	}

	public Trainer createTrainer(TrainerDTO trainer) {
		Trainer entity = TrainerMapper.toEntity(trainer);
		trainerDAO.save(entity);
		return entity;
	}

	public void deleteTrainerByUserName(String userName) {
		trainerDAO.existsByUserName(userName);
		trainerDAO.deleteEntityByUserName(userName);
	}

	public void updateTrainerFirstNameByUserName(String userName, String firstName) {
		trainerDAO.existsByUserName(userName);
		trainerDAO.findEntityByUserName(userName).ifPresent(trainer -> trainer.setFirstName(firstName));
	}


	public void updateTrainerLastNameByUserName(String userName, String lastName) {
		trainerDAO.existsByUserName(userName);
		trainerDAO.findEntityByUserName(userName).ifPresent(trainer -> trainer.setLastName(lastName));
	}

	public void updateTrainerPasswordByUserName(String userName, String password) {
		trainerDAO.existsByUserName(userName);
		trainerDAO.findEntityByUserName(userName).ifPresent(trainer -> trainer.setPassword(password));
	}

	public void updateTrainerActiveStatusByUserName(String userName, boolean isActive) {
		trainerDAO.existsByUserName(userName);
		trainerDAO.findEntityByUserName(userName).ifPresent(trainer -> trainer.setActive(isActive));
	}
}
