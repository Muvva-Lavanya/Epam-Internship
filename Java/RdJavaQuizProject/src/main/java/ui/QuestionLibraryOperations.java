package ui;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import model.Question;
import service.QuestionLibraryServicesImpl;

public class QuestionLibraryOperations {
	Logger logger = LogManager.getLogger(QuestionLibraryOptions.class);
	Scanner sc = new Scanner(System.in);
	QuestionLibraryServicesImpl questionLibraryServicesImpl = new QuestionLibraryServicesImpl();

	public void create() {
		try
		{
		int option;
		int id;
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
			logger.info("Enter option" + (counter + 1));
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
				logger.info("Choose one of the option between 0 and "+(length-1));
			}
		}while(option>length);
		Question question = new Question(title, options, difficultyLevel, tag, answer);
		logger.info(questionLibraryServicesImpl.createQuestion(question));
		}
		catch(Exception e)
		{
			logger.info(e+" Invalid input");
			sc.nextLine();
		}
	}

	public void delete() {
		try {
			logger.info("Enter an id to delete a question");
			int id = sc.nextInt();
			String message = questionLibraryServicesImpl.deleteQuestion(id);
			logger.info(message);
		} catch (Exception e) {
			logger.info(e + " Invalid input");
			sc.nextLine();
		}
	}

	public void view() {
		logger.info(questionLibraryServicesImpl.viewQuestion());
	}

	public void update() {
		logger.info("Enter an id to update");
		try {
			int id = sc.nextInt();
			while (true) {
				logger.info(
						"1.Update question title\n2.Update Option\n3.Update Difficulty Level\n4.Update Tag\n5.Update Answer\n6.Exit");
				int updateOption = sc.nextInt();
				if (updateOption == 1) {
					logger.info("Enter title to update");
					sc.nextLine();
					String title = sc.nextLine();
					if (questionLibraryServicesImpl.updateTitle(id, title)) {
						logger.info("Title updated successfully");
					} else {
						logger.info("Enter correct id");
					}
				} else if (updateOption == 2) {
					logger.info("Enter option to update");
					int option = sc.nextInt();
					logger.info("Enter value for option");
					String value = sc.next();
					if (questionLibraryServicesImpl.updateOptions(id, option, value)) {
						logger.info("Option updated successfully");
					} else {
						logger.info("Enter correct id");
					}
				} else if (updateOption == 3) {
					logger.info("Enter DifficultyLevel to update");
					logger.info("0.Easy 1.Medium 2.Hard");
					List<String> levels = Arrays.asList("Easy", "Medium", "Hard");
					int difficultyOption;
					do {
					difficultyOption = sc.nextInt();
					if(difficultyOption>=0 && difficultyOption<=2)
					{
						if (questionLibraryServicesImpl.updateDifficulty(id, levels.get(difficultyOption))) {
							logger.info("Difficulty level updated successfully");
						} else {
							logger.info("Enter correct id");
						}
					}
					else
					{
						logger.info("Choose one of the option - 0/1/2");
					}
					
					}while(difficultyOption>2);
				
				} else if (updateOption == 4) {
					logger.info("Enter Tag to update");
					String tag = sc.next();
					if (questionLibraryServicesImpl.updateTag(id, tag)) {
						logger.info("Tag updated successfully");
					} else {
						logger.info("Enter correct id");
					}
				} else if (updateOption == 5) {
					logger.info("Enter correct option for answer to update");
					int correctOption = sc.nextInt();
					if (questionLibraryServicesImpl.updateAnswer(id, correctOption)) {
						logger.info("Answer updated successfully");
					} else {
						logger.info("Enter correct id");
					}
				} else if (updateOption == 6) {
					break;
				} else {
					logger.info("Enter correct option");
				}
			}
		} catch (Exception e) {
			logger.info(e + " Invalid input");
			sc.nextLine();
		}

	}

}
