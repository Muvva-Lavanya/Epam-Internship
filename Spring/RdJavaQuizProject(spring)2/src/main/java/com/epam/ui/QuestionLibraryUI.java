package com.epam.ui;
import java.util.InputMismatchException;
import java.util.Scanner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
@Component
public class QuestionLibraryUI {
	@Autowired
	QuestionLibraryOptions questionLibraryOptions;
	public void operations() {
	    Logger logger = LogManager.getLogger(QuestionLibraryUI.class);
		Scanner sc = new Scanner(System.in);
		
		int option=0;
		do
		{
		logger.info("Select any of the following operations");
		logger.info("1. Create a question \n2. View questions \n3. Update a question \n4. Delete a question \n5. Exit");
		try
		{
		option=sc.nextInt(); 
		questionLibraryOptions.createQuestionRole(option); 
		}
		catch(InputMismatchException e)
		{
			logger.info(e);
			sc.nextLine();
		}
		}while(option!=5);
	}
 
}
