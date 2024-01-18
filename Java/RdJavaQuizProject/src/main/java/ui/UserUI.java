package ui;

import java.util.Scanner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UserUI {
	public void userService() {
		Scanner sc = new Scanner(System.in);
		Logger logger = LogManager.getLogger(AdminUI.class);
		logger.info("Choose one of the option for User");
		logger.info("1.SignUp\n2.Login\n3.Exit");
		int option = sc.nextInt();
		if (option == 1) {
			SignUpUI signUp = new SignUpUI();
			signUp.signUpService("user");
		} else if (option == 2) {
			LoginUI login = new LoginUI();
			login.loginService("user");
		}
		else if(option==3)
		{
			Main main=new Main();
			String[] args=new String[2];
			main.main(args);
		}
	}
}
