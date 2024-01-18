package com.epam.ui;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.epam.customexception.QuestionCannotBeCreatedException;
import com.epam.customexception.QuestionNotFoundException;
import com.epam.entity.Question;
import com.epam.service.QuestionLibraryServicesImpl;

import jakarta.persistence.RollbackException;
@Component
public class QuestionLibraryOperations {
	@Autowired
	QuestionLibraryServicesImpl questionLibraryServicesImpl;
	Logger logger = LogManager.getLogger(QuestionLibraryOperations.class);
	Scanner sc = new Scanner(System.in);
	public void create() { 
		try
		{
		int option;
		String title;
		int length;
		List<String> levels = Arrays.asList("Easy", "Medium", "Hard");
		String difficultyLevel = "";
		String tag;
		String answer = "";
		logger.info("Enter question title: ");
		title = sc.nextLine();
		logger.info("Enter number of options: ");
		length = sc.nextInt();
		List<String> options = new ArrayList<>();
		sc.nextLine();
		for (int counter = 0; counter < length; counter++) {
			String format=String.format("Enter option %d",(counter+1));
			logger.info(format);
			options.add(sc.nextLine());
		}
		logger.info("choose difficulty level: ");
		logger.info("0.Easy 1.Medium 2.Hard");
		do
		{
			option=sc.nextInt();
			if(option>=0 && option<=2)
			{
				difficultyLevel=levels.get(option);
			}
			else
			{
				logger.info("Choose one of the option - 0/1/2");
			}
		}while(option>2);
		
		logger.info("Enter tag for question: ");
		tag = sc.next();
		logger.info("Enter correct option i.e. answer for the question");
		do
		{
			option=sc.nextInt();
			if(option>=0 && option<=length)
			{
				answer = options.get(option);
			}
			else
			{
				logger.info("Choose proper option");
			}
		}while(option>length);
		sc.nextLine();
		Question question = new Question(title, options, difficultyLevel, tag, answer);
		questionLibraryServicesImpl.createQuestion(question);
			logger.info("Question created successfully");
		}
		catch(InputMismatchException | QuestionCannotBeCreatedException e)
		{
			logger.info(e);
			sc.nextLine();
		}
	}

	public void delete() {
		try {
			logger.info("Enter an id to delete a question");
			int id = sc.nextInt();
			sc.nextLine();
				questionLibraryServicesImpl.deleteQuestion(id);
				logger.info("Question deleted successfully");
			}
			catch(QuestionNotFoundException | RollbackException | InputMismatchException e)
			{
				logger.info(e);
				sc.nextLine();
			}
		} 
	 

	public void view() {
		List<Question> questionsList=questionLibraryServicesImpl.viewQuestion();
		if(!questionsList.isEmpty())
		{
				questionsList.stream().forEach(question->logger.info(question));
		}
		else
		{
			logger.info("No questions available");
		}
		
	}

	public void update() {
		logger.info("Enter an id to update");
		try {
			int id = sc.nextInt();
			questionLibraryServicesImpl.viewById(id);
			while (true) {
				logger.info("1.Update question title\n2.Update Option\n3.Update Difficulty Level\n4.Update Tag\n5.Update Answer\n6.Exit");
				int updateOption = sc.nextInt();
				if (updateOption == 1) {
					logger.info("Enter title to update");
					sc.nextLine();
					String title = sc.nextLine();
					String message=questionLibraryServicesImpl.updateTitle(id, title);
					logger.info(message);
					
				} else if (updateOption == 2) {
					logger.info("Enter option to update");
					int option = sc.nextInt();
					logger.info("Enter value for option");
					String value = sc.next();
					String message=questionLibraryServicesImpl.updateOptions(id, option, value);
					logger.info(message);
					
				} else if (updateOption == 3) {
					logger.info("Enter DifficultyLevel to update");
					logger.info("0.Easy 1.Medium 2.Hard");
					List<String> levels = Arrays.asList("Easy", "Medium", "Hard");
					int difficultyOption;
					difficultyOption = sc.nextInt();
					sc.nextLine();
					String message=questionLibraryServicesImpl.updateDifficulty(id,levels.get(difficultyOption));
							logger.info(message);
					
				} else if (updateOption == 4) {
					logger.info("Enter Tag to update");
					String tag = sc.next();
					String message=questionLibraryServicesImpl.updateTag(id, tag);
						logger.info(message);
					
				} else if (updateOption == 5) {
					logger.info("Enter correct option for answer to update");
					int correctOption = sc.nextInt();
					sc.nextLine();
					String message=questionLibraryServicesImpl.updateAnswer(id, correctOption);
						logger.info(message);
					
				} else if (updateOption == 6) {
					sc.nextLine();
					break;
				} else {
					logger.info("Enter correct option");
				}
			}
		}
			catch(QuestionNotFoundException | InputMismatchException e)
			{
				logger.info(e);
				sc.nextLine();
			}
	}

}
