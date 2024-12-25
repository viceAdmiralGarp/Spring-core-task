package model;

import entity.TrainingType;

public record TrainerDTO(
		long userId,
		String firstName,
		String lastName,
		String username,
		String password,
		boolean isActive,
		TrainingType trainingType
) {
}
