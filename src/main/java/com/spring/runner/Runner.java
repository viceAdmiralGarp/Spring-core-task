package com.spring.runner;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.spring.config.ApplicationConfiguration;
import com.spring.entity.Trainee;
import com.spring.repository.TraineeDAO;
import com.spring.repository.TrainingDAO;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.time.LocalDate;

public class Runner {
	public static void main(String[] args) {
		try (var context = new AnnotationConfigApplicationContext(ApplicationConfiguration.class)) {
			var storage = context.getBean(TraineeDAO.class);
			System.out.println(storage.getAll());
		}
	}
}