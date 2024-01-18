package com.epam.service;

import java.util.List;

import com.epam.customexception.QuizNotFoundException;
import com.epam.entity.Quiz;

interface QuizLibraryService {
	public Quiz addQuiz(Quiz quiz);

	public Quiz deleteQuiz(int id) throws QuizNotFoundException;

	public Quiz updateQuiz(int id, String newTitle)  throws QuizNotFoundException;

	public List<Quiz> viewQuiz();

	public Quiz viewQuizById(int id) throws QuizNotFoundException;

}
