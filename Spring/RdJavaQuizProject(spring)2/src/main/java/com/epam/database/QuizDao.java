package com.epam.database;

import java.util.List;

import com.epam.entity.Quiz;

public interface QuizDao {
	Quiz save(Quiz quiz);
	  Quiz load(int id);
	  void delete(int id);
	  Quiz update(Quiz quiz);
	  List<Quiz> loadAll();

}
