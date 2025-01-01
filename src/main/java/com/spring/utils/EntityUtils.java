package com.spring.utils;

import lombok.experimental.UtilityClass;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

@UtilityClass
public final class EntityUtils {

	private static final int PASSWORD_LENGTH = 10;
	private static final Set<String> existingUserNames = new HashSet<>();
	private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

	public static String generateUserName(String firstName, String lastName) {
		String baseUserName = "%s.%s".formatted(firstName, lastName);

		String userName = baseUserName;
		int counter = 1;

		while (existingUserNames.contains(userName)) {
			userName = baseUserName + "." + counter;
			counter++;
		}

		existingUserNames.add(userName);

		return userName;
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
}
