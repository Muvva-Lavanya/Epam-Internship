package com.epam.database;

import java.util.List;
import java.util.Optional;

import com.epam.entity.Quiz;

public interface QuizDao {
	Quiz saveQuiz(Quiz quiz);
	  Optional<Quiz> viewQuizById(int id);
	  Quiz deleteQuiz(int id);
	  Quiz updateQuiz(Quiz quiz);
	  List<Quiz> viewAllQuizzes();
	Optional<Quiz> checkQuizExists(Quiz quiz);

}
