package com.epam.ui;

import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.epam.entity.User;
import com.epam.service.SignUpService;
@Component
public class SignUpUI {
	@Autowired
	SignUpService signUpImpl;
	@Autowired
	LoginUI login;
	@Autowired
	Main main;
	public void signUpService() {
		Logger logger = LogManager.getLogger(SignUpUI.class);
		Scanner sc = new Scanner(System.in);
		String userName;
		String password;
		logger.info("Enter username");
		userName = sc.next();
		logger.info("Enter password");
		password = sc.next();
		User user=new User("user",userName, password); 
		if (signUpImpl.signUpService(user)) {
			logger.info("SignUp successfull");
			logger.info("Choose 1 to login or 0 to exit");
			int option = sc.nextInt();
			if (option == 1) {
				login.loginService("user");
			}
			else if (option == 0) {
				main.startApplication();
			}
		}

		else {
			logger.info("SignUp Unsuccessfull");
		}
	}

}
