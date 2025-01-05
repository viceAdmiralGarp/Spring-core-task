package com.spring.runner;

import com.spring.config.ApplicationConfiguration;
import com.spring.entity.Trainee;
import com.spring.entity.TrainingType;
import com.spring.model.TraineeDTO;
import com.spring.model.TrainerDTO;
import com.spring.model.TrainingDTO;
import com.spring.service.TraineeService;
import com.spring.service.TrainerService;
import com.spring.service.TrainingService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;

import static com.spring.entity.TrainingType.BODYBUILDING;


public class Runner {
	public static void main(String[] args) {

		try (var context = new AnnotationConfigApplicationContext(ApplicationConfiguration.class)) {
			var storage = context.getBean(TrainingService.class);
			TrainingDTO training1 = new TrainingDTO(
					4,
					101,                             // traineeId
					201,                             // trainerId
					"Morning Yoga",                  // trainingName
					TrainingType.YOGA,               // trainingType
					LocalDateTime.of(2025, 1, 5, 7, 30), // date
					Duration.ofMinutes(60)           // duration
			);

			TrainingDTO training2 = new TrainingDTO(
					6,
					102,                             // traineeId
					202,                             // trainerId
					"Strength Training",             // trainingName
					TrainingType.STRENGTH,           // trainingType
					LocalDateTime.of(2025, 1, 5, 9, 0),  // date
					Duration.ofMinutes(90)           // duration
			);

			storage.createTraining(training1);
			storage.updateTrainingById(4L,training2);
			System.out.println(storage.findTrainingById(4L));
		}
	}
}