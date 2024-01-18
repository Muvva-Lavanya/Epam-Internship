package com.epam.service1;
import java.util.List;

import com.epam.customexception.QuestionNotFoundException;
import com.epam.customexception.RedundantQuestionException;
import com.epam.dto.QuestionDto;
import com.epam.entity.Question;
interface QuestionLibraryService
{
	public Question createQuestion(QuestionDto questionDto) throws RedundantQuestionException;
	public Question deleteQuestion(Integer id) throws QuestionNotFoundException, NullPointerException;
	public List<Question> viewQuestion() ;
	public Question viewById(int id) throws QuestionNotFoundException;
	public Question updateQuestion(QuestionDto questionDto);
}