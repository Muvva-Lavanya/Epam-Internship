package com.epam.ui;

import java.util.Arrays;
import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.epam.customexception.RedundantQuestionException;
import com.epam.entity.Question;
import com.epam.service.QuestionLibraryServiceImpl;

import java.util.List;

@Component
public class UpdateQuestionOperations {
	@Autowired
	QuestionLibraryServiceImpl questionLibraryServicesImpl;
	Logger logger = LogManager.getLogger(UpdateQuestionOperations.class);
	Scanner sc = new Scanner(System.in);

	public void updateTitle(int questionId) {
		logger.info("Enter title to update");
		String title = sc.nextLine();
		Question updatedQuestion = new Question();
		try {
			updatedQuestion = questionLibraryServicesImpl.updateTitle(questionId, title);
		} catch (RedundantQuestionException e) {
			logger.info(e);
		}
		String message = String.format("Question title with id: %d updated successfully", updatedQuestion.getId());
		logger.info(message);
	}

	public void updateOption(int questionId) {
		logger.info("Enter option index to update");
		int optionsSize = questionLibraryServicesImpl.viewById(questionId).getOptions().size();
		int optionIndex;
		do {
			optionIndex = sc.nextInt();
			if (optionIndex > optionsSize) {
				logger.info("Enter correct option index");
			}
		} while (optionIndex > optionsSize);
		logger.info("Enter value for option");
		String value = sc.next();
		Question updatedQuestion = questionLibraryServicesImpl.updateOptions(questionId, optionIndex, value);
		String message = String.format("Question option with id: %d updated successfully", updatedQuestion.getId());
		logger.info(message);
	}

	public void updateDifficultyLevel(int questionId) {
		logger.info("Enter DifficultyLevel to update");
		logger.info("0.Easy 1.Medium 2.Hard");
		List<String> levels = Arrays.asList("Easy", "Medium", "Hard");
		int difficultyOption;
		String difficultyLevel = "";
		do {
			difficultyOption = sc.nextInt();
			if (difficultyOption >= 0 && difficultyOption <= 2) {
				difficultyLevel = levels.get(difficultyOption);
			} else {
				logger.info("Choose one of the option - 0/1/2");
			}
		} while (difficultyOption > 2);
		sc.nextLine();
		Question updatedQuestion = questionLibraryServicesImpl.updateDifficulty(questionId, difficultyLevel);
		String message = String.format("Question difficulty level with id: %d updated successfully",
				updatedQuestion.getId());
		logger.info(message);
	}

	public void updateTag(int questionId) {
		logger.info("Enter Tag to update");
		String tag = sc.next();
		Question updatedQuestion = questionLibraryServicesImpl.updateDifficulty(questionId, tag);
		String message = String.format("Question tag with id: %d updated successfully", updatedQuestion.getId());
		logger.info(message);
	}

	public void updateAnswer(int questionId) {
		int correctOption;
		int optionsSize = questionLibraryServicesImpl.viewById(questionId).getOptions().size();
		logger.info("Enter correct option i.e. answer for the question");
		do {
			correctOption = sc.nextInt();
			if (correctOption > optionsSize) {
				logger.info("Choose proper option");
			}
		} while (correctOption > optionsSize);
		sc.nextLine();
		Question updatedQuestion = questionLibraryServicesImpl.updateAnswer(questionId, correctOption);
		String message = String.format("Question answer with id: %d updated successfully", updatedQuestion.getId());
		logger.info(message);
	}

}
