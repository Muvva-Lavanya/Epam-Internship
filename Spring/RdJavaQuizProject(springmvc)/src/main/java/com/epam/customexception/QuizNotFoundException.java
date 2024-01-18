package com.epam.customexception;


public class QuizNotFoundException extends RuntimeException{

	public QuizNotFoundException() {
		super("Quiz doesnt exist");
	}

}
