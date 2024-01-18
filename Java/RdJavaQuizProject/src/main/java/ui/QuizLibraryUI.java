package ui;

import java.util.Scanner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class QuizLibraryUI {
	static int max=Integer.MAX_VALUE;
	public void operations() {
		final Logger logger = LogManager.getLogger(QuizLibraryUI.class);
		QuizLibraryUI quizLibraryUI = new QuizLibraryUI();
		String result = "";
		Scanner sc = new Scanner(System.in);
		int option=0;
		QuizLibraryOptions quizLibraryOptions=new QuizLibraryOptions();
		do
		{
		logger.info("Select any of the following CRUD operations");
		logger.info("1. Create a quiz \n2. View quiz  \n3. Delete a quiz \n4. Update a quiz \n5. Exit");
		try
		{
		option = sc.nextInt();
		quizLibraryOptions.createQuizRole(option);
		}
		catch(Exception e)
		{
			logger.info(e+" Invalid input");
			sc.next();
		}
		}while(option<max);
	}

}
