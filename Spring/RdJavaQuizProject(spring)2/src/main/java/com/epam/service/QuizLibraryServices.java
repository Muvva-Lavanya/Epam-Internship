package com.epam.service;

import java.util.List;

import com.epam.customexception.QuizNotFoundException;
import com.epam.entity.Quiz;

interface QuizLibraryServices {
	public Quiz addQuiz(Quiz quiz);

	public String deleteQuiz(int id) throws QuizNotFoundException;

	public String updateQuiz(int id, String newTitle)  throws QuizNotFoundException;

	public List<Quiz> viewQuiz();

	public Quiz viewQuizById(int id) throws QuizNotFoundException;

}
