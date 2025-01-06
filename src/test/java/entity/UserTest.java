package entity;

import com.spring.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.io.IOException;


import static org.junit.jupiter.api.Assertions.*;

public class UserTest {

	private static final Path COUNTER_FILE_PATH = Path.of("src/test/resources/counter.txt");

	@BeforeEach
	public void setUp() throws IOException {
		if (!Files.exists(COUNTER_FILE_PATH)) {
			Files.createDirectories(COUNTER_FILE_PATH.getParent());
			Files.createFile(COUNTER_FILE_PATH);
		}
		Files.writeString(COUNTER_FILE_PATH, "1");
	}

	@Test
	public void testGenerateUserNameForNewUser() {
		TestUser user1 = new TestUser(1, "John", "Doe", true);
		user1.setUsername(user1.getFirstName() + "." + user1.getLastName());
		assertEquals("John.Doe", user1.getUsername());
	}

	@Test
	public void testGenerateUserNameForDuplicateUsers() {
		TestUser user1 = new TestUser(1, "John", "Doe", true);
		TestUser user2 = new TestUser(2, "John", "Doe", true);

		String username1 = user1.generateUserNameForUserTest(user1.getFirstName(),user1.getLastName());
		String username2 = user2.generateUserNameForUserTest(user2.getFirstName(),user2.getLastName());

		assertEquals("John.Doe", username1);
		assertEquals("John.Doe.1", username2);
	}

	@Test
	public void testGeneratePassword() {
		String password = User.generatePassword();
		assertNotNull(password);
		assertEquals(10, password.length());
	}

	@Test
	public void testLoadCounterFromFileWhenFileExists() throws IOException {
		Files.writeString(COUNTER_FILE_PATH, "5");
		TestUser user1 = new TestUser(1, "Bob", "Jones", true);
		assertEquals("Bob.Jones", user1.getUsername());
	}

	@Test
	public void testLoadCounterFromFileWhenFileDoesNotExist() throws IOException {
		Path invalidPath = Path.of("invalid/counter.txt");
		if (Files.exists(invalidPath)) {
			Files.delete(invalidPath);
		}
		TestUser user1 = new TestUser(1, "Charlie", "Brown", true);
		assertEquals("Charlie.Brown", user1.getUsername());
	}
}
