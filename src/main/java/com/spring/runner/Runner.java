package com.spring.runner;

import com.spring.config.ApplicationConfiguration;
import com.spring.storage.Storage;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Runner {
	public static void main(String[] args) {
		try (var context = new AnnotationConfigApplicationContext(ApplicationConfiguration.class)) {
			var storage = context.getBean(Storage.class);
			System.out.println(storage.getStorage());
		}
	}
}