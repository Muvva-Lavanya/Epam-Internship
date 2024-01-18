package com.epam.ui;

import java.util.InputMismatchException;
import java.util.Scanner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
@Component
public class QuizLibraryUI {
	@Autowired
	QuizLibraryOptions quizLibraryOptions;
	Logger logger = LogManager.getLogger(QuizLibraryUI.class);
	public void operations() {
		Scanner sc = new Scanner(System.in);
		int option=0;
		do
		{
		logger.info("Select any of the following CRUD operations");
		logger.info("1. Create a quiz \n2. View quiz  \n3. Delete a quiz \n4. Update a quiz \n5. View quiz by Id\n6. Exit");
		try
		{
		option = sc.nextInt();
		quizLibraryOptions.selectUserOption(option);
		}
		catch(InputMismatchException e)
		{
			logger.info(e);
			sc.next();
		}
		}while(option!=6);
	}

}
