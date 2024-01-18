package com.epam.ui;

import java.util.Scanner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.epam.customexception.RedundantUserException;
@Component
public class UserUI {
	@Autowired
	SignUpUI signUp;
	@Autowired
	LoginUI login;
	@Autowired
	HomePage main;
	Logger logger = LogManager.getLogger(UserUI.class); 
	Scanner sc = new Scanner(System.in);
	public void userService() throws RedundantUserException {
		
		logger.info("Choose one of the option for User");
		logger.info("1.SignUp\n2.Login\n3.Exit");
		int option = sc.nextInt();
		if (option == 1) {
			try {
				signUp.signUpService();
			} catch (RedundantUserException e) {
				logger.info(e);
			} 
		} else if (option == 2) { 
			login.loginService("user");
		}
		else if(option==3)
		{
			main.startApplication();
		}
	}
}
