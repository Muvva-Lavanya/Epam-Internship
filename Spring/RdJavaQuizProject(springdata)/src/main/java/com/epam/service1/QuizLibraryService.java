package com.epam.service1;

import java.util.List;

import com.epam.customexception.QuizNotFoundException;
import com.epam.dto.QuizDto;
import com.epam.entity.Quiz;

interface QuizLibraryService {
	public Quiz addQuiz(QuizDto quizDto,List<Integer> questionIds);

	public Quiz deleteQuiz(int id) throws QuizNotFoundException;

	public Quiz updateQuiz(int id, String newTitle)  throws QuizNotFoundException;

	public List<Quiz> viewQuiz();

	public Quiz viewQuizById(int id) throws QuizNotFoundException;

}
