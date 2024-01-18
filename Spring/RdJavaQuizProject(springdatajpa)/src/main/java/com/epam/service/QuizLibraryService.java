package com.epam.service;

import java.util.List;

import com.epam.customexception.QuizException;
import com.epam.dto.QuizDto;
import com.epam.entity.Quiz;

interface QuizLibraryService {
	public QuizDto addQuiz(QuizDto quizDto,List<Integer> questionIds) throws QuizException;

	public QuizDto deleteQuiz(int id) throws QuizException;

	public QuizDto updateQuiz(QuizDto quizDto,List<Integer> ids);

	public List<Quiz> getAllQuizzes();

	public Quiz getQuizById(int id) throws QuizException;

}
