package com.epam.ui;

import java.util.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AdminMenu {
	@Autowired
	AdminOption adminOption;
	Logger logger = LogManager.getLogger(AdminMenu.class);
	Scanner sc = new Scanner(System.in);

	public void operations() {
		int option = 0;
		do {

			logger.info("Choose one of the option");
			logger.info("1.QuestionLibrary\n2.QuizLibrary\n3.Exit");
			try {
				option = sc.nextInt();
				adminOption.selectUserOption(option);
			} catch (InputMismatchException e) {
				logger.info(e);
				sc.next();
			}
		} while (option != 3);

	}

}
