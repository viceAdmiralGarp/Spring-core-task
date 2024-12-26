package service;

import entity.Trainer;
import lombok.RequiredArgsConstructor;
import mapper.trainer.TrainerMapper;
import model.TrainerDTO;
import org.springframework.stereotype.Service;
import repository.TrainerDAO;

import java.util.List;
import java.util.Optional;

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

	public void updateTrainerFirstNameByUserName(String userName, String firstName) {
		trainerDAO.entityDoesntExistsByUserName(userName);
		trainerDAO.findEntityByUserName(userName).ifPresent(trainer -> trainer.setFirstName(firstName));
	}

	public void updateTrainerLastNameByUserName(String userName, String lastName) {
		trainerDAO.entityDoesntExistsByUserName(userName);
		trainerDAO.findEntityByUserName(userName).ifPresent(trainer -> trainer.setLastName(lastName));
	}

	public void updateTrainerUserNameByUserName(String userName, String newUserName) {
		trainerDAO.entityDoesntExistsByUserName(userName);
		trainerDAO.entityExistsByUserName(newUserName);

		Optional<Trainer> entityByUserName = trainerDAO.findEntityByUserName(userName);

		if (entityByUserName.isPresent()) {
			trainerDAO.deleteEntityByUserName(userName);
			Trainer trainer = entityByUserName.get();
			trainer.setUsername(newUserName);
			trainerDAO.save(trainer);
		}
	}

	public void updateTrainerPasswordByUserName(String userName, String password) {
		trainerDAO.entityDoesntExistsByUserName(userName);
		trainerDAO.findEntityByUserName(userName).ifPresent(trainer -> trainer.setPassword(password));
	}

	public void updateTrainerActiveStatusByUserName(String userName, boolean isActive) {
		trainerDAO.entityDoesntExistsByUserName(userName);
		trainerDAO.findEntityByUserName(userName).ifPresent(trainer -> trainer.setActive(isActive));
	}
}
