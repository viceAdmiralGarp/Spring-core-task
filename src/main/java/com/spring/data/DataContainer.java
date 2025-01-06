package com.spring.data;

import com.spring.entity.Entity;
import com.spring.entity.Trainee;
import com.spring.entity.Trainer;
import com.spring.entity.Training;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Data
@Component
public class DataContainer {
	private static final Logger logger = LoggerFactory.getLogger(DataContainer.class);
	private List<Entity<?>> entities = new ArrayList<>();

	public void setTraining(List<Training> training) {
		logger.info("Adding {} trainings to entities.", training.size());
		entities.addAll(training);
		logger.debug("Entities after adding trainings: {}", entities);
	}


	public void setTrainers(List<Trainer> trainers) {
		logger.info("Adding {} trainers to entities.", trainers.size());
		entities.addAll(trainers);
		logger.debug("Entities after adding trainers: {}", entities);

	}

	public void setTrainees(List<Trainee> trainees) {
		logger.info("Adding {} trainees to entities.", trainees.size());
		entities.addAll(trainees);
		logger.debug("Entities after adding trainees: {}", entities);
	}
}