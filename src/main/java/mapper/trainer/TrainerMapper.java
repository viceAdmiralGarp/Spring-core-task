package mapper.trainer;

import entity.Trainer;
import model.TrainerDTO;

public class TrainerMapper {

	public static TrainerDTO toDto(Trainer trainer) {
		return new TrainerDTO(
				trainer.getUserId(),
				trainer.getFirstName(),
				trainer.getLastName(),
				trainer.getUsername(),
				trainer.getPassword(),
				trainer.isActive(),
				trainer.getTrainingType()
		);
	}

	public static Trainer toEntity(TrainerDTO trainerDTO) {
		return new Trainer(
				trainerDTO.userId(),
				trainerDTO.firstName(),
				trainerDTO.lastName(),
				trainerDTO.username(),
				trainerDTO.password(),
				trainerDTO.isActive(),
				trainerDTO.trainingType()
		);
	}
}
