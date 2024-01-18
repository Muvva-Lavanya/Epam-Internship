package ui;

import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import model.User;
import service.LoginService;

public class LoginUI {

	public void loginService(String userType) {
		LoginUI loginUI = new LoginUI();
		Logger logger = LogManager.getLogger(LoginUI.class);
		Scanner sc = new Scanner(System.in);
		String userName, password;
		logger.info("Enter " + userType + " username");
		userName = sc.next();
		logger.info("Enter " + userType + " password");
		password = sc.next();
		LoginService userLoginImpl = new LoginService();
		User user=new User(userName, password);
		if (userType.equals("admin")) {
			if (userLoginImpl.validateUser(userType,user)) {
				logger.info(userType + " Login Succesfull");
				AdminMenu adminMenu = new AdminMenu();
				adminMenu.operations();
			} else {
				logger.info(userType + " Login Unsuccesfull");
				loginUI.loginService("admin");
			}
		} else {
			if (userLoginImpl.validateUser(userType,user)) {
				logger.info(userType + " Login Succesfull");
			} else {
				logger.info(userType + " Login Unsuccesfull");
			}
		}

	}

}
