package com.epam.customexception;

public class AuthenticationFailureException extends RuntimeException{
	public AuthenticationFailureException(String message) {
		super(message);
	}

}
