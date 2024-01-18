package com.epam.ui;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class HomePage {

	@Autowired
	UserSelection userSelection;
	Logger logger = LogManager.getLogger(HomePage.class);
	Scanner sc = new Scanner(System.in);

	public void startApplication() {
		int option = 0;
		do {
			logger.info("Select the type of role");
			logger.info("1.Admin\n2.User\n3.Exit");
			try {
				option = sc.nextInt();
				userSelection.selectUserOption(option);
			} catch (InputMismatchException e) {
				logger.info(e);
			}
		} while (option != 3);
	}

}
