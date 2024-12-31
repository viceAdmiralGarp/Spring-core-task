package com.spring.dataloader;

import com.spring.entity.Trainee;
import com.spring.entity.Trainer;
import com.spring.entity.Training;
import com.spring.entity.TrainingType;
import com.spring.entity.User;
import com.spring.storage.Storage;
import com.spring.utils.EntityUtils;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Component
@RequiredArgsConstructor
public class DataLoader {

	@Value("${storage.trainers.file}")
	private String trainersFilePath;

	@Value("${storage.trainees.file}")
	private String traineesFilePath;

	@Value("${storage.trainings.file}")
	private String trainingsFilePath;

	private final Storage storage;

	@PostConstruct
	public void loadData() {
		try {
			processTrainerFile();
			processTraineeFile();
			processTrainingFile();
		} catch (IOException e) {
			throw new RuntimeException("Failed to initialize data from files", e);
		}
	}

	private void processTrainerFile() throws IOException {
		List<String> lines = readFile(trainersFilePath);
		for (String line : lines) {
			Trainer trainer = parseTrainer(line.split(","));
			storage.addTrainer(trainer.getUsername(), trainer);
		}
	}

	private void processTraineeFile() throws IOException {
		List<String> lines = readFile(traineesFilePath);
		for (String line : lines) {
			Trainee trainee = parseTrainee(line.split(","));
			storage.addTrainee(trainee.getUsername(), trainee);
		}
	}

	private void processTrainingFile() throws IOException {
		List<String> lines = readFile(trainingsFilePath);
		for (String line : lines) {
			Training training = parseTraining(line.split(","));
			storage.addTraining(training.getTrainingName(), training);
		}
	}

	public List<String> readFile(String filePath) throws IOException {
		return Files.readAllLines(Paths.get(filePath));
	}

	private Trainer parseTrainer(String[] parts) {
		User data = User.extractUserData(parts);
		TrainingType trainingType = Enum.valueOf(TrainingType.class, parts[5]);
		return new Trainer(data.getUserId(), data.getFirstName(), data.getLastName(),
				data.getUsername(), data.getPassword(), data.isActive(), trainingType);
	}

	private Trainee parseTrainee(String[] parts) {
		User data = User.extractUserData(parts);
		String program = parts[5];
		LocalDate enrollmentDate = LocalDate.parse(parts[6]);
		return new Trainee(data.getUserId(), data.getFirstName(), data.getLastName(),
				data.getUsername(), data.getPassword(), data.isActive(), program, enrollmentDate);
	}

	private Training parseTraining(String[] parts) {
		long traineeId = Long.parseLong(parts[1]);
		long trainerId = Long.parseLong(parts[2]);
		String trainingName = parts[3];
		TrainingType trainingType = Enum.valueOf(TrainingType.class, parts[4]);
		LocalDateTime date = LocalDateTime.parse(parts[5]);
		Duration duration = Duration.parse(parts[6]);
		return new Training(traineeId, trainerId, trainingName, trainingType, date, duration);
	}
}
