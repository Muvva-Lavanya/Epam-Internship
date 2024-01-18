package ui;

import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import model.User;
import service.SignUpService;

public class SignUpUI {
	public void signUpService(String userType) {
		Logger logger = LogManager.getLogger(SignUpUI.class);
		Scanner sc = new Scanner(System.in);
		String userName, password;
		logger.info("Enter " + userType + " username");
		userName = sc.next();
		logger.info("Enter " + userType + " password");
		password = sc.next();
		SignUpService signUpImpl = new SignUpService();
		User user=new User(userName, password);
		if (signUpImpl.signUpService(userType,user)) {
			logger.info("SignUp successfull");
			logger.info("Choose 1 to login or 0 to exit");
			int option = sc.nextInt();
			if (option == 1 && userType.equals("user")) {
				LoginUI login = new LoginUI();
				login.loginService("user");
			} else if (option == 1 && userType.equals("admin")) {
				LoginUI login = new LoginUI();
				login.loginService("admin");
			} else if (option == 0) {
				Main.main(null);
			}
		}

		else {
			logger.info("SignUp Unsuccessfull");
		}
	}

}
