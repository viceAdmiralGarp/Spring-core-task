package com.spring.entity;

import com.spring.utils.EntityUtils;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class User {

	private long userId;
	private String firstName;
	private String lastName;
	private String username;
	private String password;
	private boolean isActive;

	public User(long userId, String firstName, String lastName, String username, String password, boolean isActive) {
		this.userId = userId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.password = password;
		this.isActive = isActive;
	}

	public static User extractUserData(String[] parts) {
		long userId = Long.parseLong(parts[1]);
		String firstName = parts[2];
		String lastName = parts[3];
		String userName = EntityUtils.generateUserName(firstName, lastName);
		String password = EntityUtils.generatePassword();
		boolean isActive = Boolean.parseBoolean(parts[4]);
		return new User(userId, firstName, lastName, userName, password, isActive);
	}
}
