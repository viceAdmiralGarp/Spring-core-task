package com.spring.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.Duration;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class Training extends Entity<Long> {
	private long traineeId;
	private long trainerId;
	private String trainingName;
	private TrainingType trainingType;
	private LocalDateTime date;
	private Duration duration;

	public Training(Long id, long traineeId, long trainerId, String trainingName, TrainingType trainingType, LocalDateTime date, Duration duration) {
		super(id);
		this.traineeId = traineeId;
		this.trainerId = trainerId;
		this.trainingName = trainingName;
		this.trainingType = trainingType;
		this.date = date;
		this.duration = duration;
	}
}
