package com.spring.runner;

import com.spring.config.ApplicationConfiguration;
import com.spring.model.TrainingDTO;
import com.spring.repository.TraineeDAO;
import com.spring.service.TrainingService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.time.Duration;
import java.time.LocalDateTime;

import static com.spring.entity.TrainingType.BODYBUILDING;

public class Runner {
	public static void main(String[] args) {
		try (var context = new AnnotationConfigApplicationContext(ApplicationConfiguration.class)) {
			var storage = context.getBean(TraineeDAO.class);
			System.out.println(storage.getAll());

		}
	}
}