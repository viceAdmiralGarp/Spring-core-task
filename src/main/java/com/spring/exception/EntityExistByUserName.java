package com.spring.exception;

public class EntityExistByUserName extends RuntimeException {
	public EntityExistByUserName(String message) {
		super(message);
	}
}
