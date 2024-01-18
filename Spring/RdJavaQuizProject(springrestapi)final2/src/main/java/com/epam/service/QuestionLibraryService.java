package com.epam.service;
import java.util.List;

import com.epam.customexception.QuestionException;
import com.epam.dto.QuestionDto;
import com.epam.entity.Question;
interface QuestionLibraryService
{
	public QuestionDto createQuestion(QuestionDto questionDto) throws QuestionException;
	public void deleteQuestion(Integer questionId);
	public List<Question> getQuestions() ;
	public QuestionDto getQuestionById(int id) throws QuestionException;
	public QuestionDto updateQuestion(QuestionDto questionDto) throws QuestionException;
}