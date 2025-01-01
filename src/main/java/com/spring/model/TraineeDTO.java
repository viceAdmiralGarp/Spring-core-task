package com.spring.model;

import java.time.LocalDate;

public record TraineeDTO(
		long userId,
		String firstName,
		String lastName,
		boolean isActive,
		String address,
		LocalDate dateOfBirth) {
}
