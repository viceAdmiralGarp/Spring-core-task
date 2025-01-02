package com.spring.service;

import com.spring.entity.Trainer;
import com.spring.entity.Training;
import com.spring.model.TrainerDTO;
import com.spring.model.TrainingDTO;
import com.spring.repository.TrainerDAO;
import com.spring.repository.TrainingDAO;
import com.spring.utils.EntityUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TrainingService {

	private final TrainingDAO trainingDAO;

	public List<Training> getAllTrainers() {
		return trainingDAO.getAll();
	}

	public Training findTrainerById(Long id) {
		return trainingDAO.findEntityById(id)
				.orElseThrow(() -> new NullPointerException(
						"Trainer with ID: '%s' not found".formatted(id))
				);
	}

	public void createTraining(TrainingDTO trainingDTO) {
		Training training = new Training(trainingDTO.trainingId(), trainingDTO.traineeId(), trainingDTO.trainerId(), trainingDTO.trainingName(), trainingDTO.trainingType(), trainingDTO.date(), trainingDTO.duration());
		trainingDAO.save(String.valueOf(training.getTrainingId()), training);
	}

	public void deleteTrainerById(Long id) {
		trainingDAO.deleteEntityById(id);
	}

	public void updateTrainerById(Long id, TrainingDTO trainingDTO) {
		Training training = findTrainerById(id);

		training.setTrainingId(trainingDTO.trainingId());
		training.setTraineeId(trainingDTO.traineeId());
		training.setTrainerId(trainingDTO.trainerId());
		training.setTrainingName(trainingDTO.trainingName());
		training.setTrainingType(trainingDTO.trainingType());
		training.setDate(trainingDTO.date());
		training.setDuration(trainingDTO.duration());
	}
}
