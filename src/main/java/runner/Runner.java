package runner;

import entity.TrainingType;
import model.TrainerDTO;
import repository.TrainerDAO;
import service.TrainerService;

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
		trainerService.deleteTrainerByUserName("johndoe");
		trainerService.updateTrainerFirstNameByUserName("janesmith","123");
		System.out.println(trainerService.getAllTrainers());
	}
}
