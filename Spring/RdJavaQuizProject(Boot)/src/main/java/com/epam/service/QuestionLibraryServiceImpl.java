package com.epam.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import com.epam.customexception.RedundantQuestionException;
import com.epam.customexception.QuestionNotFoundException;
import com.epam.database.QuestionDao;
import com.epam.entity.Question;

@Service
public class QuestionLibraryServiceImpl implements QuestionLibraryService {
	@Autowired
	@Qualifier(value = "questionDaoJpaImpl")
	private QuestionDao questionDaoJpaImpl;

	@Override
	public Question createQuestion(Question question) throws RedundantQuestionException {
		if (questionDaoJpaImpl.checkQuestionExists(question.getTitle()).isEmpty()) {
			return questionDaoJpaImpl.saveQuestion(question);
		} else {
			throw new RedundantQuestionException();
		}
	}

	@Override
	public Question deleteQuestion(Integer questionId) throws QuestionNotFoundException {
		return questionDaoJpaImpl.deleteQuestion(questionId);
	}

	@Override
	public List<Question> viewQuestion() {
		return questionDaoJpaImpl.viewAllQuestions();
	}

	@Override
	public Question updateAnswer(int id, int correctOption) {
		Question updatedQuestion = questionDaoJpaImpl.viewQuestionById(id);
		updatedQuestion.setAnswer(updatedQuestion.getOptions().get(correctOption));
		return questionDaoJpaImpl.updateQuestion(updatedQuestion);
	}

	@Override
	public Question updateTitle(int id, String title) throws RedundantQuestionException {
		if (questionDaoJpaImpl.checkQuestionExists(title).isEmpty()) {
			Question updatedQuestion = questionDaoJpaImpl.viewQuestionById(id);
			updatedQuestion.setTitle(title);
			return questionDaoJpaImpl.updateQuestion(updatedQuestion);
		} else {
			throw new RedundantQuestionException();
		}
	}

	@Override
	public Question updateDifficulty(int id, String difficulty) {
		Question updatedQuestion = questionDaoJpaImpl.viewQuestionById(id);
		updatedQuestion.setDifficultyLevel(difficulty);
		return questionDaoJpaImpl.updateQuestion(updatedQuestion);
	}

	@Override
	public Question updateTag(int id, String tag) {
		Question updatedQuestion = questionDaoJpaImpl.viewQuestionById(id);
		updatedQuestion.setTag(tag);
		return questionDaoJpaImpl.updateQuestion(updatedQuestion);
	}

	@Override
	public Question updateOptions(int id, int optionNo, String value) {
		Question updatedQuestion = questionDaoJpaImpl.viewQuestionById(id);
		updatedQuestion.getOptions().set(optionNo - 1, value);
		return questionDaoJpaImpl.updateQuestion(updatedQuestion);
	}

	@Override
	public Question viewById(int id) {
		return questionDaoJpaImpl.viewQuestionById(id);
	}
	
	@Override
	public List<Integer> getAllQuestionIds()
	{
		return questionDaoJpaImpl.getAllQuestionIds();
	}

}