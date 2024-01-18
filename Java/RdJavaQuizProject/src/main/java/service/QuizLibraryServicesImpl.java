package service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;
import database.QuestionLibraryDao;
import database.QuizLibraryDao;
import model.Question;
import model.Quiz;

public class QuizLibraryServicesImpl implements QuizLibraryServices {

	@Override
	public String addQuiz(String title, int numberOfQuestions) {
		List<Quiz> quizList = new ArrayList<>();
		Map<String, Integer> marks = new HashMap<>();
		{
			marks.put("Easy", 2);
			marks.put("Medium", 3);
			marks.put("Hard", 5);
		}
		QuestionLibraryDao questionLibraryDao = new QuestionLibraryDao();
		Map<Integer, Question> allQuestions = questionLibraryDao.getQuestionData();
		Set<Entry<Integer, Question>> questionsSet = allQuestions.entrySet();
		List<Question> questionList = new ArrayList<>();
		for (Entry<Integer, Question> counter : questionsSet) {
			questionList.add(counter.getValue());
		}
		List<String> titles = questionList.stream().map(e -> e.getTag()).distinct().toList();
		long count = questionList.stream().map(e -> e.getTag().equals(title)).count();
		String result = "Quiz cannot be created";
		int length = 0;
		if (titles.contains(title) && numberOfQuestions <= count) {
			for (Question counter : questionList) {
				if (counter.getTag().equals(title) && length < numberOfQuestions) {
					length += 1;
					quizList.add(new Quiz(counter.getTitle(), counter.getOptions(), counter.getDifficultyLevel(),
							marks.get(counter.getDifficultyLevel())));
				}

			}
			QuizLibraryDao quizLibraryDao = new QuizLibraryDao();
			quizLibraryDao.setQuiz(title, quizList);
			result = "Created quiz successfully";
		}
		return result;
	}

	@Override
	public String deleteQuiz(String title) {
		String result = "Quiz doesnt exist";
		QuizLibraryDao quizLibraryDao = new QuizLibraryDao();
		Map<String, List<Quiz>> quizData = quizLibraryDao.getQuizDatabase();
		if (quizData.containsKey(title)) {
			quizLibraryDao.deleteQuizData(title);
			result = "Quiz deleted successfully";
		}
		return result;
	}

	@Override
	public String updateQuiz(String oldTitle, String newTitle) {
		String result = "Enter correct title/ title doesn't exist";
		QuizLibraryDao quizLibraryDao = new QuizLibraryDao();
		Map<String, List<Quiz>> quizData = quizLibraryDao.getQuizDatabase();
		if (quizData.containsKey(oldTitle)) {
			List<Quiz> temp = quizData.get(oldTitle);
			quizData.remove(oldTitle);
			quizData.put(newTitle, temp);
			result = "Title updated successfully";
		}
		return result;
	}

	@Override
	public StringBuffer viewQuiz() {
		QuizLibraryDao quizLibraryDao = new QuizLibraryDao();
		Map<String, List<Quiz>> quizData = quizLibraryDao.getQuizDatabase();
		StringBuffer result = new StringBuffer();
		if (quizData.size() == 0) {
			result.append("No quizzes available");
		} else {
			Set<Entry<String, List<Quiz>>> quizDataSet = quizData.entrySet();
			int quizIncrement = 1;
			for (Entry<String, List<Quiz>> counter : quizDataSet) {
				List<Quiz> quizDataList = counter.getValue();
				result.append(
						"Quiz " + quizIncrement + ". " + counter.getKey() + " - " + quizDataList.size() + "Questions"
								+ " - " + quizDataList.stream().mapToInt(e -> e.getMarks()).sum() + "Marks" + "\n");
				int questionIncrement = 1;
				for (Quiz quizCounter : quizDataList) {
					result.append(questionIncrement + "." + quizCounter.getQuestion() + "("
							+ quizCounter.getDifficultyLevel() + ")\n");
					List<String> option = quizCounter.getOptions();
					int optionIncrement = 1;
					for (String optionCounter : option) {
						result.append("Option" + optionIncrement + ". " + optionCounter + "\n");
						optionIncrement += 1;
					}
					questionIncrement += 1;
				}
				quizIncrement += 1;
				result.append("\n");
			}
		}
		return result;
	}

}
