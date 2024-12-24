package runner;

import entity.Trainer;

import static entity.TrainingType.YOGA;

public class Runner {
	public static void main(String[] args) {
		Trainer trainer1 = new Trainer(1,"vova", "vova", "123", "123", true,  YOGA);
		Trainer trainer2 = new Trainer(1,"vova", "vova", "123", "123", true,  YOGA);
		System.out.println(trainer1.equals(trainer2));

	}
}
