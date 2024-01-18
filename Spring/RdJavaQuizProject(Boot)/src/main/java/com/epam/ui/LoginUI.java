package com.epam.ui;

import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.epam.entity.User;
import com.epam.service.LoginService;

@Component
public class LoginUI {
	@Autowired
	LoginService userLoginImpl;
	@Autowired
	AdminMenu adminMenu;
	Logger logger = LogManager.getLogger(LoginUI.class);
	Scanner sc = new Scanner(System.in);

	public void loginService(String userType) {
		String userName;
		String password;
		logger.info("Enter username");
		userName = sc.next();
		logger.info("Enter password");
		password = sc.next();
		User user = new User(userType, userName, password);
		if (userType.equals("admin")) {
			if (userLoginImpl.validateUser(user)) {
				logger.info("Login Succesfull");
				adminMenu.operations();
			} else {
				logger.info(" Login Unsuccesfull");
				logger.info("Please enter correct details");
				loginService("admin");
			}
		} else if (userType.equals("user")) {
			if (userLoginImpl.validateUser(user)) {
				logger.info(" Login Succesfull");


			} else {
				logger.info(" Login Unsuccesfull");
			}
		}

	}

}
