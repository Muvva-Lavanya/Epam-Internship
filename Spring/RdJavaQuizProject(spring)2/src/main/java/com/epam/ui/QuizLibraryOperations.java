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
import com.epam.customexception.RedundantException;
import com.epam.entity.Question;
import com.epam.entity.Quiz;
import com.epam.service.QuestionLibraryServicesImpl;
import com.epam.service.QuizLibraryServicesImpl;
@Component
public class QuizLibraryOperations {
	@Autowired
	QuizLibraryServicesImpl quizLibraryServicesImpl;
	@Autowired
	QuestionLibraryServicesImpl questionLibraryServicesImpl;
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
			List<Question> availableQuestionList=questionLibraryServicesImpl.viewQuestion();
			if(numberOfQuestions<=availableQuestionList.size())
			{
				while (numberOfQuestions > 0) {
					logger.info("Available questions are:");
					logger.info(availableQuestionList.stream().map(e -> e.getId()).toList());
					logger.info("Enter question id:");
					int id = sc.nextInt();
					Question question = availableQuestionList.stream().filter(e -> e.getId() == id).findFirst()
							.orElse(null);
					if (question != null) {
						questionList.add(question);
						availableQuestionList.remove(availableQuestionList.indexOf(question));
						numberOfQuestions -= 1;
					} else {
						logger.info("please choose from available questions only");
					}
				}
				Quiz quiz = new Quiz(title, questionList.size(), countTotalMarks(questionList), questionList);
				quizLibraryServicesImpl.addQuiz(quiz);
				logger.info("Quiz created successfully");
			} else {
				logger.info("Insufficient number of Questions");
			}
		} catch (InputMismatchException | RedundantException e) {
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
			String message=quizLibraryServicesImpl.deleteQuiz(id);
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
			String message=quizLibraryServicesImpl.updateQuiz(id, newTitle);
			logger.info(message);
		} catch (QuizNotFoundException e) {
			logger.info(e);
		}
	}

	public void viewQuizByTitle() {
		try {
			logger.info("Enter quiz id to view");
			int id = sc.nextInt();
			sc.nextLine();
			Quiz quizById = quizLibraryServicesImpl.viewQuizById(id);
			logger.info(quizById);
			List<Question> questionInQuiz = quizById.getQuestion();
			questionInQuiz.stream().forEach(question->logger.info(question));
		} catch (QuizNotFoundException e) {
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
				counter.getQuestion().stream().forEach(question->logger.info(question)); 
			}
		}
	}
}
