package com.epam.customexception;

public class NoSuchQuestionException extends RuntimeException {
	public NoSuchQuestionException(String message) {
		super(message);
	}

}
