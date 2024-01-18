package ui;

import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import service.QuizLibraryServicesImpl;

public class QuizLibraryOperations {
	final Logger logger = LogManager.getLogger(QuestionLibraryOptions.class);
	Scanner sc=new Scanner(System.in);
	QuizLibraryServicesImpl quizLibraryServicesImpl=new QuizLibraryServicesImpl();
	public void create()
	{	
		try {
		int numberOfQuestions;
		logger.info("Enter title of the quiz");
		String title = sc.next();
		logger.info("Enter number of Questions");
		numberOfQuestions = sc.nextInt();
		logger.info(quizLibraryServicesImpl.addQuiz(title, numberOfQuestions));
		}
		catch(Exception e)
		{
			logger.info(e+" Invalid input");
			sc.nextLine();
		}
	}
	public void view()
	{
		logger.info(quizLibraryServicesImpl.viewQuiz());
	}
	public void delete()
	{
		try
		{
		logger.info("Enter title to delete the quiz");
		String title = sc.next();
		logger.info(quizLibraryServicesImpl.deleteQuiz(title));
		}
		catch(Exception e)
		{
			logger.info(e+" Invalid input");
			sc.nextLine();
		}
		
	}
	public void update()
	{
		sc.nextLine();
		logger.info("Enter old title");
		String oldTitle = sc.nextLine();
		logger.info("Enter new title");
		String newTitle = sc.nextLine();
		logger.info(quizLibraryServicesImpl.updateQuiz(oldTitle, newTitle));
	}
}
