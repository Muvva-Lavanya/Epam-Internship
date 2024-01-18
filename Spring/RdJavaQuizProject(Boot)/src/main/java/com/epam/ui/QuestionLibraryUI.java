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
	@Autowired
	UpdateQuestionOptions updateQuestionOptions;
	 Logger logger = LogManager.getLogger(QuestionLibraryUI.class);
	 Scanner sc = new Scanner(System.in);
	public void operations() { 
		int option=0;
		do
		{
		logger.info("Select any of the following operations");
		logger.info("1. Create a question \n2. View questions \n3. Update a question \n4. Delete a question \n5. Exit");
		try
		{
		option=sc.nextInt(); 
		questionLibraryOptions.selectUserOption(option); 
		}
		catch(InputMismatchException e)
		{
			logger.info(e);
			sc.nextLine();
		}
		}while(option!=5);
	}
	public void updateType(int questionId) {
		int option = 0;
		do {
			logger.info(
					"1.Update question title\n2.Update Option\n3.Update Difficulty Level\n4.Update Tag\n5.Update Answer\n6.Exit");
			try {
				option=sc.nextInt();
				updateQuestionOptions.selectUserOption(option, questionId);
			}
			catch(InputMismatchException e)
			{
				logger.info(e);
			}
		}while(option!=6);
	}
 
}
