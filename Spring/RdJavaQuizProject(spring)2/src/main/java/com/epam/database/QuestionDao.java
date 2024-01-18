package com.epam.database;

import java.util.List;

import com.epam.entity.Question;

public interface QuestionDao {
	Question save(Question question);
	  Question load(int id);
	  void delete(int id);
	  Question update(Question question);
	  List<Question> loadAll();
}
