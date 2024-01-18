package com.epam.database;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.epam.entity.Question;
import java.util.Collections;

@Repository
public class QuestionDaoCollectionsImpl implements QuestionDao {
	static int id = Data.questionLibraryStorage.size() + 1;
	private static Map<Integer, Question> questionDatabase = Data.questionLibraryStorage;

	@Override
	public Question saveQuestion(Question question) {
		return questionDatabase.put(question.getId(), question);

	}

	@Override
	public Question viewQuestionById(int id) {
		return questionDatabase.get(id);
	}

	@Override
	public Question deleteQuestion(int id) {
		return questionDatabase.remove(id);

	}

	@Override
	public Question updateQuestion(Question question) {
		return questionDatabase.put(question.getId(), question);

	}

	@Override
	public List<Question> viewAllQuestions() {
		return questionDatabase.values().stream().toList();
	}

	@Override
	public Optional<Question> checkQuestionExists(String questionTitle) {
		return Optional.empty();
	}

	@Override
	public List<Integer> getAllQuestionIds() {
		return Collections.emptyList();
	}

}
