package com.epam.ui;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.epam.customexception.QuizNotFoundException;
import com.epam.customexception.RedundantQuizException;
import com.epam.entity.Question;
import com.epam.entity.Quiz;
import com.epam.service.QuestionLibraryServiceImpl;
import com.epam.service.QuizLibraryServiceImpl;

@Component
public class QuizLibraryOperations {
	@Autowired
	QuizLibraryServiceImpl quizLibraryServicesImpl;
	@Autowired
	QuestionLibraryServiceImpl questionLibraryServicesImpl;
	Logger logger = LogManager.getLogger(QuizLibraryOperations.class);
	Scanner sc = new Scanner(System.in);

	public void create() {
		try {
			int numberOfQuestions;
			logger.info("Enter title of the quiz");
			String title = sc.next();
			logger.info("Enter number of Questions");
			numberOfQuestions = sc.nextInt();
			List<Question> questionList = new ArrayList<>();
			List<Integer> availableQuestionIds = questionLibraryServicesImpl.getAllQuestionIds();
			if (numberOfQuestions <= availableQuestionIds.size()) {
				while (numberOfQuestions > 0) {
					logger.info("Available questions are:");
					logger.info(availableQuestionIds);
					logger.info("Enter question id:");
					int id = sc.nextInt();
					Integer questionId = availableQuestionIds.stream().filter(e -> e == id).findFirst().orElse(null);
					if (questionId != null) {
						questionList.add(questionLibraryServicesImpl.viewById(questionId));
						availableQuestionIds.remove(availableQuestionIds.indexOf(questionId));
						numberOfQuestions -= 1;
					} else {
						logger.info("please choose from available questions only");
					}
				}
				Quiz quiz = new Quiz(title, questionList.size(), countTotalMarks(questionList), questionList);
				Quiz savedQuiz = quizLibraryServicesImpl.addQuiz(quiz);
				String format = String.format("Quiz with id: %d created successfully", savedQuiz.getId());
				logger.info(format);
			} else {
				logger.info("Insufficient number of Questions");
			}
		} catch (InputMismatchException | RedundantQuizException e) {
			logger.info(e);
			sc.nextLine();
		}
	}

	private long countTotalMarks(List<Question> quizList) {
		long easy = quizList.stream().filter(e -> e.getDifficultyLevel().equalsIgnoreCase("easy")).count();
		long medium = quizList.stream().filter(e -> e.getDifficultyLevel().equalsIgnoreCase("medium")).count();
		long hard = quizList.stream().filter(e -> e.getDifficultyLevel().equalsIgnoreCase("hard")).count();
		return ((easy * 2) + (medium * 3) + (hard * 5));
	}

	public void delete() {
		try {
			logger.info("Enter quiz id to delete the quiz");
			int id = sc.nextInt();
			Quiz deletedQuiz = quizLibraryServicesImpl.deleteQuiz(id);
			String message = String.format("Quiz with id: %d deleted successfully", deletedQuiz.getId());
			logger.info(message);
		} catch (QuizNotFoundException | InputMismatchException e) {
			logger.info(e);
			sc.nextLine();
		}

	}

	public void update() {
		logger.info("Enter id of quiz to update");
		int id = sc.nextInt();
		sc.nextLine();
		logger.info("Enter new title");
		String newTitle = sc.nextLine();
		try {
			Quiz updatedQuiz = quizLibraryServicesImpl.updateQuiz(id, newTitle);
			String message = String.format("Quiz title with id: %d updated successfully", updatedQuiz.getId());
			logger.info(message);
		} catch (QuizNotFoundException | InputMismatchException e) {
			logger.info(e);
		}
	}

	public void viewQuizById() {
		try {
			logger.info("Enter quiz id to view");
			int id = sc.nextInt();
			sc.nextLine();
			Quiz quizById = quizLibraryServicesImpl.viewQuizById(id);
			logger.info(quizById);
			List<Question> questionInQuiz = quizById.getQuestion();
			questionInQuiz.stream().forEach(question -> logger.info(question));
		} catch (QuizNotFoundException | InputMismatchException e) {
			logger.info(e);
		}

	}

	public void view() {
		List<Quiz> quizData = quizLibraryServicesImpl.viewQuiz();
		if (quizData.isEmpty()) {
			logger.info("No Quizzes available");
		} else {
			for (Quiz counter : quizData) {
				logger.info(counter);
				counter.getQuestion().stream().forEach(question -> logger.info(question));
			}
		}
	}
}
