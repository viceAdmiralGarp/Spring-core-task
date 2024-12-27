package com.spring.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Duration;
import java.time.LocalDateTime;


@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Training {

	private long traineeId;
	private long trainerId;
	private String trainingName;
	private TrainingType trainingType;
	private LocalDateTime date;
	private Duration duration;

}
