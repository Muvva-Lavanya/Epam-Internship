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

import com.epam.customexception.RedundantQuestionException;
import com.epam.customexception.QuestionNotFoundException;
import com.epam.entity.Question;
import com.epam.service.QuestionLibraryServiceImpl;

import jakarta.persistence.RollbackException;

@Component
public class QuestionLibraryOperations {
	@Autowired
	QuestionLibraryServiceImpl questionLibraryServicesImpl;
	@Autowired
	UpdateQuestionOptions updateQuestionOptions;
	@Autowired
	QuestionLibraryUI questionLibraryUI;
	Logger logger = LogManager.getLogger(QuestionLibraryOperations.class);
	Scanner sc = new Scanner(System.in);

	public void create() {
		try {
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
				String format = String.format("Enter option %d", (counter + 1));
				logger.info(format);
				options.add(sc.nextLine());
			}
			logger.info("choose difficulty level: ");
			logger.info("0.Easy 1.Medium 2.Hard");
			do {
				option = sc.nextInt();
				if (option >= 0 && option <= 2) {
					difficultyLevel = levels.get(option);
				} else {
					logger.info("Choose one of the option - 0/1/2");
				}
			} while (option > 2);

			logger.info("Enter tag for question: ");
			tag = sc.next();
			logger.info("Enter correct option i.e. answer for the question");
			do {
				option = sc.nextInt();
				if (option >= 0 && option <= length) {
					answer = options.get(option);
				} else {
					logger.info("Choose proper option");
				}
			} while (option > length);
			sc.nextLine();
			Question question = new Question(title, options, difficultyLevel, tag, answer);
			Question savedQuestion = questionLibraryServicesImpl.createQuestion(question);
			String format = String.format("Question with id: %d created successfully", savedQuestion.getId());
			logger.info(format);
		} catch (InputMismatchException | RedundantQuestionException e) {
			logger.info(e);
			sc.nextLine();
		}
	}

	public void delete() {
		try {
			logger.info("Enter an id to delete a question");
			int id = sc.nextInt();
			sc.nextLine();
			Question deletedQuestion = questionLibraryServicesImpl.deleteQuestion(id);
			String message = String.format("Question with id: %d deleted successfully", deletedQuestion.getId());
			logger.info(message);
		} catch (QuestionNotFoundException | RollbackException | InputMismatchException e) {
			logger.info(e);
			sc.nextLine();
		}
	}

	public void view() {
		List<Question> questionsList = questionLibraryServicesImpl.viewQuestion();
		if (!questionsList.isEmpty()) {
			questionsList.stream().forEach(question -> logger.info(question));
		} else {
			logger.info("No questions available");
		}

	}

	public void update() {
		logger.info("Enter an id to update");
		try {
			int questionId=sc.nextInt();
			questionLibraryServicesImpl.viewById(questionId);
			questionLibraryUI.updateType(questionId);
		}
		catch(QuestionNotFoundException |InputMismatchException e)
		{
			logger.info(e);
		}
	}

}
