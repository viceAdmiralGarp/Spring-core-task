package com.spring.mapper.trainer;

import com.spring.model.TrainerDTO;
import com.spring.entity.Trainer;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
public class TrainerMapper {

	public static TrainerDTO toDto(Trainer trainer) {
		return new TrainerDTO(
				trainer.getUserId(),
				trainer.getFirstName(),
				trainer.getLastName(),
				trainer.isActive(),
				trainer.getTrainingType()
		);
	}

	public static Trainer toEntity(TrainerDTO trainerDTO,String userName, String password) {
		return new Trainer(
				trainerDTO.userId(),
				trainerDTO.firstName(),
				trainerDTO.lastName(),
				userName,
				password,
				trainerDTO.isActive(),
				trainerDTO.trainingType()
		);
	}
}
