package com.spring.model;

import com.spring.entity.TrainingType;

public record TrainerDTO(
		long userId,
		String firstName,
		String lastName,
		boolean active,
		TrainingType trainingType
) {
}
