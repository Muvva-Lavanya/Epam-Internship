package database;

import java.util.List;
import java.util.Map;

import model.Quiz;

public class QuizLibraryDao {
	private static Map<String, List<Quiz>> quizDatabase = Data.quizLibraryStorage;
	public void setQuiz(String title, List<Quiz> quizList) {
		quizDatabase.put(title, quizList);
	}

	public Map<String, List<Quiz>> getQuizDatabase() {
		return quizDatabase;
	}
	
	public void deleteQuizData(String quizTitle)
	{
		quizDatabase.remove(quizTitle);
	}

}
