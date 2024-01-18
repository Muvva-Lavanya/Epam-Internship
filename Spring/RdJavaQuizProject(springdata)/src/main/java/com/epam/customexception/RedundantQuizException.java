package com.epam.customexception;

public class RedundantQuizException extends RuntimeException {
	 public RedundantQuizException() {
		super("Quiz already exists");
	}
}
