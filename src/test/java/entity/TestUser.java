package entity;

import com.spring.entity.User;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.Set;

public class TestUser extends User {

	private static final Path COUNTER_FILE_PATH = Path.of("src/test/resources/counter.txt");
	private static final Set<String> existingUserNames = new HashSet<>();
	private static int counter = 1;

	public TestUser(long userId, String firstName, String lastName, boolean active) {
		super(userId, firstName, lastName, active);
	}

	public String generateUserNameForUserTest(String firstName, String lastName) {
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

	private static void saveCounterToFile() {
		try {
			Files.writeString(COUNTER_FILE_PATH, String.valueOf(counter));
		} catch (IOException e) {
			System.out.println("Could not save counter to file.");
		}
	}
}
