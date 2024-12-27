package com.spring.runner;

import com.spring.model.TrainerDTO;
import com.spring.entity.TrainingType;
import com.spring.repository.TrainerDAO;
import com.spring.service.TrainerService;

public class Runner {
	public static void main(String[] args) {
		TrainerDAO trainerDAO = new TrainerDAO();
		TrainerService trainerService = new TrainerService(trainerDAO);
		TrainerDTO trainer1 = new TrainerDTO(1L, "John", "Doe", "johndoe", "password123", true, TrainingType.YOGA);
		TrainerDTO trainer2 = new TrainerDTO(2L, "Jane", "Smith", "janesmith", "secret", false, TrainingType.BODYBUILDING);
		TrainerDTO trainer3 = new TrainerDTO(3L, "David", "Lee", "davidlee", "pass456", true, TrainingType.FLEXIBILITY);
		trainerService.createTrainer(trainer1);
		trainerService.createTrainer(trainer2);
		trainerService.createTrainer(trainer3);
		System.out.println(trainerService.findByTrainerByUsername("johndoe"));

	}
}
