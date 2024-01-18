package com.epam.customexception;

public class RedundantException extends RuntimeException {
	 public RedundantException() {
		super("Quiz already exists");
	}
}
