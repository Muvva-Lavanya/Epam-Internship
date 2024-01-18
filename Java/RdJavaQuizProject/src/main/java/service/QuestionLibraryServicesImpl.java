package service;

import java.util.Map;
import java.util.Map.Entry;

import database.QuestionLibraryDao;

import java.util.Set;

import model.Question;

public class QuestionLibraryServicesImpl implements QuestionLibraryServices {

	@Override
	public String createQuestion(Question question) {
		QuestionLibraryDao questionLibraryDao = new QuestionLibraryDao();
		questionLibraryDao.setQuestionData(question);
		return "Question created successfully";
	}

	@Override
	public String deleteQuestion(Integer questionId) {
		String result="Question id [" + questionId + "]" + " doesnt exist";
		QuestionLibraryDao questionLibraryDao = new QuestionLibraryDao();
		Map<Integer, Question> allQuestions = questionLibraryDao.getQuestionData();
		if (allQuestions.containsKey(questionId)) {
			questionLibraryDao.deleteQuestionData(questionId);
			result = "Question deleted successfully";
		}
		return result;
	}

	@Override
	public StringBuffer viewQuestion() {
		StringBuffer result = new StringBuffer();
		QuestionLibraryDao questionLibraryDao = new QuestionLibraryDao();
		Map<Integer, Question> allQuestions = questionLibraryDao.getQuestionData();
		if (allQuestions.size() == 0) {
			result.append("There are no questions to display");
		} else {
			Set<Entry<Integer, Question>> allQuestionsSet = allQuestions.entrySet();
			int increment = 1;
			for (Entry<Integer, Question> counter : allQuestionsSet) {
				Question question = counter.getValue();
				result.append(increment + ". " + question);
				increment += 1;
			}
		}

		return result;
	}

	@Override
	public boolean updateAnswer(int id, int correctOption) {
		boolean result = false;
		QuestionLibraryDao questionLibraryDao = new QuestionLibraryDao();
		Map<Integer, Question> allQuestions = questionLibraryDao.getQuestionData();
		if (allQuestions.containsKey(id)) {
			Question updateQuestion = allQuestions.get(id);
			updateQuestion.setAnswer(updateQuestion.getOptions().get(correctOption));
			questionLibraryDao.updateQuestionData(id, updateQuestion);
			result = true;
		}
		return result;
	}

	@Override
	public boolean updateTitle(int id, String title) {
		boolean result = false;
		QuestionLibraryDao questionLibraryDao = new QuestionLibraryDao();
		Map<Integer, Question> allQuestions = questionLibraryDao.getQuestionData();
		if (allQuestions.containsKey(id)) {
			Question updateQuestion = allQuestions.get(id);
			updateQuestion.setTitle(title);
			questionLibraryDao.updateQuestionData(id, updateQuestion);
			result = true;
		}
		return result;
	}

	@Override
	public boolean updateDifficulty(int id, String difficulty) {
		boolean result = false;
		QuestionLibraryDao questionLibraryDao = new QuestionLibraryDao();
		Map<Integer, Question> allQuestions = questionLibraryDao.getQuestionData();
		if (allQuestions.containsKey(id)) {
			Question updateQuestion = allQuestions.get(id);
			updateQuestion.setDifficultyLevel(difficulty);
			questionLibraryDao.updateQuestionData(id,updateQuestion);
			result = true;
		}
		return result;
	}

	@Override
	public boolean updateTag(int id, String tag) {
		boolean result = false;
		QuestionLibraryDao questionLibraryDao = new QuestionLibraryDao();
		Map<Integer, Question> allQuestions = questionLibraryDao.getQuestionData();
		if (allQuestions.containsKey(id)) {
			Question updateQuestion = allQuestions.get(id);
			updateQuestion.setTag(tag);
			questionLibraryDao.updateQuestionData(id,updateQuestion);
			result = true;
		}
		return result;

	}

	@Override
	public boolean updateOptions(int id, int optionNo, String value) {
		boolean result = false;
		QuestionLibraryDao questionLibraryDao = new QuestionLibraryDao();
		Map<Integer, Question> allQuestions = questionLibraryDao.getQuestionData();
		if (allQuestions.containsKey(id)) {
			Question updateQuestion = allQuestions.get(id);
			updateQuestion.getOptions().set(optionNo - 1, value);
			questionLibraryDao.updateQuestionData(id,updateQuestion);
			result = true;
		}
		return result;
	}

}