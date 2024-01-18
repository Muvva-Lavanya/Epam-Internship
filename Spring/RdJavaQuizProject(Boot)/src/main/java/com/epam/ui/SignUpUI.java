package com.epam.ui;

import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.epam.customexception.RedundantUserException;
import com.epam.entity.User;
import com.epam.service.SignUpService;

@Component
public class SignUpUI {
	@Autowired
	SignUpService signUpImpl;
	@Autowired
	LoginUI login;
	@Autowired
	HomePage main;
	Logger logger = LogManager.getLogger(SignUpUI.class);

	public void signUpService() throws RedundantUserException {
		Scanner sc = new Scanner(System.in);
		String userName;
		String password;
		logger.info("Enter username");
		userName = sc.next();
		logger.info("Enter password");
		password = sc.next();
		User user = new User("user", userName, password);
		try {
			User savedUser = signUpImpl.signUpService(user);
			String format=String.format("Welcome %s",savedUser.getUsername());
			logger.info(format);
			logger.info("Choose 1 to login or 0 to exit");
			int option = sc.nextInt();
			if (option == 1) {
				login.loginService("user");
			} else if (option == 0) {
				main.startApplication();
			}
		} catch (RedundantUserException e) {
			logger.info(e);
		}

	}

}
