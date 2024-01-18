package com.epam.customexception;

public class RedundantUserException extends Exception {
	 public RedundantUserException() {
			super("User already exists");
		}
}
