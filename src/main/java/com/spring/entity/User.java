package com.spring.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public abstract class User extends Entity<String> {

	private long userId;
	private String firstName;
	private String lastName;
	private String username;
	private String password;
	private boolean isActive;

	public User(long userId, String firstName, String lastName, String username, String password, boolean isActive) {
		super(username);
		this.userId = userId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.password = password;
		this.isActive = isActive;
	}
}
