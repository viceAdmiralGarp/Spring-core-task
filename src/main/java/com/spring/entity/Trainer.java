package com.spring.entity;

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

	public Trainer(long id,
				   String firstName,
				   String lastName,
				   String username,
				   String password,
				   boolean active,
				   TrainingType trainingType) {
		super(id, firstName, lastName, username, password, active);
		this.trainingType = trainingType;
	}
}
