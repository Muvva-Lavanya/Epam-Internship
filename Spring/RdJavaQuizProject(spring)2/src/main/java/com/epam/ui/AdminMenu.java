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
	public void operations() {
		Logger logger = LogManager.getLogger(AdminMenu.class); 
		int option=0;
		do
		{
		Scanner sc = new Scanner(System.in);
		logger.info("Choose one of the option");
		logger.info("1.QuestionLibrary\n2.QuizLibrary\n3.Exit");
		try
		{
		option = sc.nextInt();
		adminOption.createAdminRole(option);
		}
		catch(InputMismatchException e)
		{
			logger.info(e);
			sc.next();
		}
		}while(option!=3);

	}

}
