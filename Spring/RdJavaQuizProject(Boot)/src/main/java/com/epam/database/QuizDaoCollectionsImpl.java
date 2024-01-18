package com.epam.database;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.epam.entity.Quiz;
@Repository
public class QuizDaoCollectionsImpl implements QuizDao{
	static int id = Data.quizLibraryStorage.size()+1;
	private static Map<Integer,Quiz> quizDatabase = Data.quizLibraryStorage;
	@Override
	public Quiz saveQuiz(Quiz quiz) {
		return quizDatabase.put(quiz.getId(),quiz);
		
	}

	@Override
	public Quiz viewQuizById(int id) {
		return (quizDatabase.get(id));
	}

	@Override
	public Quiz deleteQuiz(int id) {
		return quizDatabase.remove(id);
	}

	@Override
	public Quiz updateQuiz(Quiz quiz) {
		return quizDatabase.put(quiz.getId(), quiz);
	}

	@Override
	public List<Quiz> viewAllQuizzes() {
		return quizDatabase.values().stream().toList();
	}

	@Override
	public Optional<Quiz> checkQuizExists(Quiz quiz) {
		return Optional.empty();
	}



}