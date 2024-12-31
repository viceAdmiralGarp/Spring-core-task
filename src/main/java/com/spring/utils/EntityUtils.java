package com.spring.utils;

import lombok.experimental.UtilityClass;

import java.util.Random;

@UtilityClass
public final class EntityUtils {

	private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
	private static final int PASSWORD_LENGTH = 10;


	public static String generateUserName(String firstName, String lastName) {
		return "%s.%s".formatted(firstName, lastName);
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
