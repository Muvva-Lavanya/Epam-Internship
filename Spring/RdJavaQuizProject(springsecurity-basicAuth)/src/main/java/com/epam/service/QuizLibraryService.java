package com.epam.service;

import java.util.List;

import com.epam.customexception.QuizException;
import com.epam.dto.QuizDto;
import com.epam.entity.Quiz;

interface QuizLibraryService {
	public QuizDto addQuiz(QuizDto quizDto,List<Integer> questionIds) throws QuizException;

	public void deleteQuiz(int id);

	public QuizDto updateQuiz(QuizDto quizDto,List<Integer> ids) throws QuizException;

	public List<Quiz> getAllQuizzes();

	public QuizDto getQuizById(int id) throws QuizException;

}
