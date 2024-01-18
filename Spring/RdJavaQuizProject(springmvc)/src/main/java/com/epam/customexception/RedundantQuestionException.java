package com.epam.customexception;

public class RedundantQuestionException extends Exception {
	public RedundantQuestionException() {
		super("Question already exists");
	}
}
