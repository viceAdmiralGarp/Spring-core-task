package com.spring.service;

import com.spring.entity.Training;
import com.spring.exception.EntityNotFoundException;
import com.spring.mapper.TrainingMapper;
import com.spring.model.TrainingDTO;
import com.spring.repository.TrainingDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TrainingService {

	private final TrainingDAO trainingDAO;

	public List<Training> getAllTrainings() {
		return trainingDAO.getAll();
	}

	public Training findTrainingById(Long id) {
		return trainingDAO.findEntityById(id)
				.orElseThrow(() -> new EntityNotFoundException(
						"Training with ID: '%s' not found".formatted(id))
				);
	}

	public void createTraining(TrainingDTO trainingDTO) {
		trainingDAO.save(TrainingMapper.INSTANCE.toEntity(trainingDTO));
	}

	public void deleteTrainingById(Long id) {
		Training trainer = findTrainingById(id);
		trainingDAO.deleteEntity(trainer);
	}

	public void updateTrainingById(Long id, TrainingDTO trainingDTO) {
		Training training = findTrainingById(id);

		training.setTraineeId(trainingDTO.traineeId());
		training.setTrainerId(trainingDTO.trainerId());
		training.setTrainingName(trainingDTO.trainingName());
		training.setTrainingType(trainingDTO.trainingType());
		training.setDate(trainingDTO.date());
		training.setDuration(trainingDTO.duration());
	}
}
