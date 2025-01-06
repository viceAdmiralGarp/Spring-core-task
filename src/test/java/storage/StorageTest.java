package storage;

import com.spring.entity.Entity;
import com.spring.storage.Storage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class StorageTest {

	private Storage storage;

	@BeforeEach
	void initStorage(){
		this.storage = new Storage();
	}

	@Test
	public void testAddEntity_shouldStoreEntity() {
		TestEntity entity = new TestEntity(1, "Entity1");

		storage.addEntity(1, entity);

		List<TestEntity> storedEntities = storage.getEntities(TestEntity.class);
		assertEquals(1, storedEntities.size(), "There should be one entity stored.");
		assertEquals("Entity1", storedEntities.get(0).getName(), "The stored entity should have the correct name.");
	}

	@Test
	public void testGetEntities_shouldReturnCorrectEntities() {
		TestEntity entity1 = new TestEntity(1, "Entity1");
		TestEntity entity2 = new TestEntity(2, "Entity2");

		storage.addEntity(1, entity1);
		storage.addEntity(2, entity2);

		List<TestEntity> storedEntities = storage.getEntities(TestEntity.class);

		assertEquals(2, storedEntities.size(), "There should be two entities stored.");
		assertTrue(storedEntities.contains(entity1), "Entity1 should be in the list.");
		assertTrue(storedEntities.contains(entity2), "Entity2 should be in the list.");
	}

	@Test
	public void testGetEntities_shouldReturnEmptyListForEmptyStorage() {
		List<TestEntity> storedEntities = storage.getEntities(TestEntity.class);
		assertTrue(storedEntities.isEmpty(), "The storage should be empty.");
	}

	@Test
	public void testAddEntity_shouldNotMixDifferentEntityTypes() {
		TestEntity entity1 = new TestEntity(1, "Entity1");
		AnotherEntity entity2 = new AnotherEntity(2, "Another Entity");

		storage.addEntity(1, entity1);
		storage.addEntity(2, entity2);

		List<TestEntity> storedTestEntities = storage.getEntities(TestEntity.class);
		List<AnotherEntity> storedAnotherEntities = storage.getEntities(AnotherEntity.class);

		assertEquals(1, storedTestEntities.size(), "There should be one TestEntity.");
		assertEquals(1, storedAnotherEntities.size(), "There should be one AnotherEntity.");
	}

	static class TestEntity extends Entity<Integer> {
		private String name;

		public TestEntity(Integer id, String name) {
			super(id);
			this.name = name;
		}

		public String getName() {
			return name;
		}
	}

	static class AnotherEntity extends Entity<Integer> {
		private String description;

		public AnotherEntity(Integer id, String description) {
			super(id);
			this.description = description;
		}

		public String getDescription() {
			return description;
		}
	}
}