package repository;

import entity.Trainer;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class TrainerDAO implements CrudRepository<Trainer,String> {

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
}
