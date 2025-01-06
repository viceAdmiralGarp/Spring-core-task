package com.spring.runner;

import com.spring.config.ApplicationConfiguration;
import com.spring.data.DataContainer;
import com.spring.service.TraineeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Runner {
	private static final Logger logger = LoggerFactory.getLogger(DataContainer.class);
	public static void main(String[] args) {
		try (AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfiguration.class)) {
			TraineeService bean = context.getBean(TraineeService.class);
			System.out.println(bean.findTraineeById(101L));
		}

	}
}
