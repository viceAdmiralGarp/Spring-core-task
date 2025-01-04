package com.spring.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class Trainer extends User {

	private TrainingType trainingType;

	public Trainer(long userId,
				   String firstName,
				   String lastName,
				   boolean active,
				   TrainingType trainingType) {
		super(userId, firstName, lastName, active);
		this.trainingType = trainingType;
	}
}
