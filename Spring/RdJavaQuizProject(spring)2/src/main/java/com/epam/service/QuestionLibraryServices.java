package com.epam.service;
import java.util.List;
import com.epam.customexception.QuestionCannotBeCreatedException;
import com.epam.customexception.QuestionNotFoundException;
import com.epam.entity.Question;
interface QuestionLibraryServices
{
	public Question createQuestion(Question question) throws QuestionCannotBeCreatedException;
	public String deleteQuestion(Integer questionId) throws QuestionNotFoundException, NullPointerException;
	public List<Question> viewQuestion() ;
	public Question viewById(int id) throws QuestionNotFoundException;
	public String updateTitle(int id, String title) throws QuestionNotFoundException;
	public String updateAnswer(int id,  int correctOption) throws QuestionNotFoundException;
	public String updateDifficulty(int id, String difficulty) throws QuestionNotFoundException;
	public String updateTag(int id, String taggingTopics) throws QuestionNotFoundException;
	public String updateOptions(int id,int option,String value) throws QuestionNotFoundException;
}