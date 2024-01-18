package com.epam.customexception;

public class QuestionCannotBeCreatedException extends Exception {
	public QuestionCannotBeCreatedException() {
		super("Question already exists");
	}
}
