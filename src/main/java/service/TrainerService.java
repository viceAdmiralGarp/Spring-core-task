package service;

import entity.Trainer;
import lombok.RequiredArgsConstructor;
import mapper.trainer.TrainerMapper;
import model.TrainerDTO;
import org.springframework.stereotype.Service;
import repository.TrainerDAO;

import java.util.List;
import java.util.function.Consumer;

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
		Trainer trainer = trainerDAO.findEntityByUserName(userName)
				.orElseThrow(() -> new NullPointerException(
						"Trainer with userName: '%s' not found".formatted(userName))
				);
		updateFunction.accept(trainer);
	}
}
