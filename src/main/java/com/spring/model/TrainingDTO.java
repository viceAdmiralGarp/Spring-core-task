package com.spring.model;

import com.spring.entity.TrainingType;

import java.time.Duration;
import java.time.LocalDateTime;

public record TrainingDTO(
		long id,
		long traineeId,
		long trainerId,
		String trainingName,
		TrainingType trainingType,
		LocalDateTime date,
		Duration duration) {
}
