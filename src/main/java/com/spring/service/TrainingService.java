package com.spring.service;

import com.spring.entity.Training;
import com.spring.exception.EntityNotFoundException;
import com.spring.mapper.TrainingMapper;
import com.spring.model.TrainingDTO;
import com.spring.repository.TraineeDAO;
import com.spring.repository.TrainingDAO;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TrainingService {

	private final TrainingDAO trainingDAO;
	private static final Logger logger = LoggerFactory.getLogger(TrainingService.class);

	public List<Training> getAllTrainings() {
		return trainingDAO.getAll();
	}

	public Training findTrainingById(Long id) {
		return trainingDAO.findEntityById(id)
				.orElseThrow(() -> {
					String errorMessage = "Training with ID: '%s' not found".formatted(id);
					logger.error(errorMessage);
					return new EntityNotFoundException(errorMessage);
				});
	}

	public void createTraining(TrainingDTO trainingDTO) {
		Training entity = TrainingMapper.INSTANCE.toEntity(trainingDTO);
		trainingDAO.save(entity);
		logger.info("Created training session with ID: {}", entity.getId());
	}

	public void deleteTrainingById(Long id) {
		Training trainer = findTrainingById(id);
		trainingDAO.deleteEntity(trainer);
		logger.info("Deleted training session with ID: {}", id);
	}

	public void updateTrainingById(Long id, TrainingDTO trainingDTO) {
		Training training = findTrainingById(id);

		training.setTraineeId(trainingDTO.traineeId());
		training.setTrainerId(trainingDTO.trainerId());
		training.setTrainingName(trainingDTO.trainingName());
		training.setTrainingType(trainingDTO.trainingType());
		training.setDate(trainingDTO.date());
		training.setDuration(trainingDTO.duration());
		logger.info("Updated training session with ID: {}: {}", id, training);
	}
}
