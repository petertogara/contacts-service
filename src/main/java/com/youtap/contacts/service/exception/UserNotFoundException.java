package com.youtap.contacts.service.exception;

public class UserNotFoundException extends RuntimeException {

	public UserNotFoundException(String id) {

		super(id);
	}
}
