package repository;

import entity.Trainer;
import exception.EntityDoesntExistByUserName;
import exception.EntityExistByUserName;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class TrainerDAO implements CrudRepository<Trainer, String> {

	private final Map<String, Trainer> trainerRepository = new HashMap<>();

	@Override
	public List<Trainer> getAll() {
		return new ArrayList<>(trainerRepository.values());
	}

	@Override
	public Optional<Trainer> findEntityByUserName(String userName) {
		return Optional.ofNullable(trainerRepository.get(userName));
	}

	@Override
	public Trainer save(Trainer entity) {
		return trainerRepository.put(entity.getUsername(), entity);
	}

	@Override
	public void deleteEntityByUserName(String userName) {
		trainerRepository.remove(userName);
	}

	@Override
	public void updateUserName(String userName, String newUserName) {
		findEntityByUserName(userName)
				.ifPresent(trainer -> {
					deleteEntityByUserName(userName);
					trainer.setUsername(newUserName);
					save(trainer);
				});
	}

	@Override
	public void entityDoesntExistsByUserName(String userName) {
		if (findEntityByUserName(userName).isEmpty())
			throw new EntityDoesntExistByUserName(
					"Entity with userName: '%s' doesn't exist".formatted(userName));
	}

	@Override
	public void entityExistsByUserName(String userName) {
		if (findEntityByUserName(userName).isPresent()) {
			throw new EntityExistByUserName(
					"Entity with userName: '%s' already exist".formatted(userName));
		}
	}
}
