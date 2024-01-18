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
	public void loginService(String userType) {
		Logger logger = LogManager.getLogger(LoginUI.class);
		Scanner sc = new Scanner(System.in); 
		String userName;
		String password;
		logger.info("Enter username");
		userName = sc.next();
		logger.info("Enter password");
		password = sc.next();
		User admin=new User(userType,userName,password);
		if (userType.equals("admin")) {
		
			if (userLoginImpl.validateUser(admin)) {
				logger.info("Login Succesfull");
				adminMenu.operations();
			} else {
				logger.info(" Login Unsuccesfull");
				loginService("admin");
			}
		} else {
			if (userLoginImpl.validateUser(admin)) {
				logger.info(" Login Succesfull");
			} else {
				logger.info(" Login Unsuccesfull");
			}
		}

	}

}
