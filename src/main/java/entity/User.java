package entity;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public abstract class User {

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
}
