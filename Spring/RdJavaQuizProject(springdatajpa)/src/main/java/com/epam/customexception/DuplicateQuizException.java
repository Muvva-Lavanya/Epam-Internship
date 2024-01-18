package com.epam.customexception;

public class DuplicateQuizException extends RuntimeException {
	 public DuplicateQuizException(String message) {
		super(message);
	}
}
