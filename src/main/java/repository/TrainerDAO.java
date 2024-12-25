package repository;

import entity.Trainer;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class TrainerDAO {

	private final Map<String, Trainer> trainerRepository = new HashMap<>();

	public List<Trainer> getAllTrainers() {
		return new ArrayList<>(trainerRepository.values());
	}

	public Optional<Trainer> findTrainerByUsername(String userName) {
		return Optional.ofNullable(trainerRepository.get(userName));
	}

	public Trainer save(Trainer trainer){
		return trainerRepository.put(trainer.getUsername(),trainer);
	}

	public void deleteTrainerByUserName(String userName) {
		trainerRepository.remove(userName);
	}

	public void updateTrainerNameByUserName(String newName, String userName){

	}

}
