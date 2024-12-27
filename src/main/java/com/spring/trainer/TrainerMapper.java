package com.spring.trainer;

import com.spring.model.TrainerDTO;
import com.spring.entity.Trainer;

public class TrainerMapper {

	private static final TrainerMapper INSTANCE = new TrainerMapper();

	private TrainerMapper() {
	}

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

	public static TrainerMapper getInstance(){
		return INSTANCE;
	}
}
