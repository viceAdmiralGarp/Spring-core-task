package com.spring.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class Trainee extends User {

	private String address;
	private LocalDate dateOfBirth;

	public Trainee(long userId,
				   String firstName,
				   String lastName,
				   boolean active,
				   String address,
				   LocalDate dateOfBirth) {
		super(userId, firstName, lastName, active);
		this.address = address;
		this.dateOfBirth = dateOfBirth;
	}
}
