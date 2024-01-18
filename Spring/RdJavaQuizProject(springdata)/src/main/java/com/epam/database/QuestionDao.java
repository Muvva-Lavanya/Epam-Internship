package com.epam.database;

import java.util.List;
import java.util.Optional;

import com.epam.customexception.QuestionNotFoundException;
import com.epam.entity.Question;

public interface QuestionDao {
	Question saveQuestion(Question question);
	  Optional<Question> viewQuestionById(int id) throws QuestionNotFoundException;
	  Question deleteQuestion(int id);
	  Question updateQuestion(Question question);
	  List<Question> viewAllQuestions();
	Optional<Question> checkQuestionExists(String questionTitle);
}
