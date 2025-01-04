package com.spring.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public abstract class User extends Entity<String> {

	private static int counter = 1;
	private static final int PASSWORD_LENGTH = 10;
	private static final Path PATH = Path.of("src/main/resources/counter.txt");
	private static final String COUNTER_FILE = "resources/counter.txt";
	private static final Set<String> existingUserNames = new HashSet<>();
	private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

	private long userId;
	private String firstName;
	private String lastName;
	private String username;
	private String password;
	private boolean active;

	public User(long userId, String firstName, String lastName, boolean active) {
		this.userId = userId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = generateUserName(firstName, lastName);
		this.password = generatePassword();
		this.active = active;
	}

	static {
		loadCounterFromFile();
	}

	public static String generateUserName(String firstName, String lastName) {
		String baseUserName = "%s.%s".formatted(firstName, lastName);

		String userName = baseUserName;
		while (existingUserNames.contains(userName)) {
			userName = baseUserName + "." + counter;
			counter++;
		}

		existingUserNames.add(userName);
		saveCounterToFile();
		return userName;
	}

	private static void loadCounterFromFile() {
		try {
			String line = Files.readString(PATH);
			if (line != null) {
				counter = Integer.parseInt(line);
			}
		} catch (IOException | NumberFormatException e) {
			System.out.println("Could not load counter from file, starting at 1.");

		}
	}

	private static void saveCounterToFile() {
		try {
			Files.writeString(PATH, String.valueOf(counter));
		} catch (IOException e) {
			System.out.println("Could not save counter to file.");
		}
	}

	public static String generatePassword() {
		StringBuilder stringBuilder = new StringBuilder();
		Random random = new Random();
		for (int i = 0; i < PASSWORD_LENGTH; i++) {
			int index = random.nextInt(CHARACTERS.length());
			stringBuilder.append(CHARACTERS.charAt(index));
		}
		return stringBuilder.toString();
	}

	public void setUsername(String username) {
		this.username = username;
		this.setId(username);
	}
}
