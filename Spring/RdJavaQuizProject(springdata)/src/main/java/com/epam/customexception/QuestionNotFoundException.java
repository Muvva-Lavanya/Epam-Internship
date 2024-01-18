package com.epam.customexception;

public class QuestionNotFoundException extends RuntimeException{
	public QuestionNotFoundException() {
		super("Question id doesnt exists");
	}

}
