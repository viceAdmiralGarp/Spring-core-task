package com.spring.runner;

import com.spring.config.ApplicationConfiguration;
import com.spring.entity.Trainer;
import com.spring.entity.TrainingType;
import com.spring.model.TrainerDTO;
import com.spring.service.TrainerService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Runner {
	public static void main(String[] args) {
		try (var context = new AnnotationConfigApplicationContext(ApplicationConfiguration.class)) {
			var storage = context.getBean(TrainerService.class);
			Trainer trainer = new Trainer(1, "Jon", "kek", "kekes", "123", true, TrainingType.YOGA);
			TrainerDTO trainerDto = new TrainerDTO(2, "lol", "kek", true, TrainingType.YOGA);
			TrainerDTO trainerDto1 = new TrainerDTO(3, "lol", "kek", true, TrainingType.YOGA);
			storage.createTrainer(trainerDto);
			storage.createTrainer(trainerDto1);
			System.out.println(storage.findTrainerById(2L));
			System.out.println(storage.findTrainerById(3L));
		}
	}
}