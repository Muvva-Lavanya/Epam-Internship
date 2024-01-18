package com.epam.customexception;

public class DuplicateQuestionException extends Exception {
	public DuplicateQuestionException(String message) {
		super(message);
	}
}
