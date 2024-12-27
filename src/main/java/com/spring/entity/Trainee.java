package com.spring.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Trainee extends User{

	private String address;
	private LocalDate dateOfBirth;

	public Trainee(long userId,
				   String firstName,
				   String lastName,
				   String username,
				   String password,
				   boolean isActive,
				   String address,
				   LocalDate dateOfBirth) {
		super(userId, firstName, lastName, username, password, isActive);
		this.address = address;
		this.dateOfBirth = dateOfBirth;
	}

}
