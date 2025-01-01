package com.spring.model;

import com.spring.entity.TrainingType;

public record TrainerDTO(
		long userId,
		String firstName,
		String lastName,
		boolean isActive,
		TrainingType trainingType
) {
}
