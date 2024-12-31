package com.spring.runner;

import com.spring.config.ApplicationConfiguration;
import com.spring.repository.TrainerDAO;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Runner {
	public static void main(String[] args) {
		try (var context = new AnnotationConfigApplicationContext(ApplicationConfiguration.class)) {
			var storage = context.getBean(TrainerDAO.class);
			System.out.println(storage.findEntityById(102L));
		}
	}
}