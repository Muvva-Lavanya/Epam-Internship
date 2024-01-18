package com.epam.customexception;

public class RedundantUserException extends RuntimeException{
	 public RedundantUserException() {
			super("User already exists");
		}
}
