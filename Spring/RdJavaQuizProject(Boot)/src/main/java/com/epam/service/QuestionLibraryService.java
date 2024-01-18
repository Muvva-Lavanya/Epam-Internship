package com.epam.service;
import java.util.List;

import com.epam.customexception.RedundantQuestionException;
import com.epam.customexception.QuestionNotFoundException;
import com.epam.entity.Question;
interface QuestionLibraryService
{
	public Question createQuestion(Question question) throws RedundantQuestionException;
	public Question deleteQuestion(Integer questionId) throws QuestionNotFoundException, NullPointerException;
	public List<Question> viewQuestion() ;
	public Question viewById(int id) throws QuestionNotFoundException;
	public Question updateTitle(int id, String title) throws RedundantQuestionException;
	public Question updateAnswer(int id,  int correctOption) ;
	public Question updateDifficulty(int id, String difficulty) ;
	public Question updateTag(int id, String taggingTopics) ;
	public Question updateOptions(int id,int option,String value);
	List<Integer> getAllQuestionIds();
}