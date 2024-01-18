package com.epam.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.epam.customexception.QuestionNotFoundException;
import com.epam.customexception.RedundantQuestionException;
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
		return questionDaoJpaImpl.viewQuestionById(questionId)
				.map(q-> questionDaoJpaImpl.deleteQuestion(questionId))
				.orElseThrow(QuestionNotFoundException::new);
	}

	@Override
	public List<Question> viewQuestion() {
		return questionDaoJpaImpl.viewAllQuestions();
	}

	@Override
	public Question updateAnswer(int id,String answer) {
		return questionDaoJpaImpl.viewQuestionById(id)
				.map(q->{
					q.setAnswer(answer);
					return questionDaoJpaImpl.updateQuestion(q);
				}).orElseThrow(QuestionNotFoundException::new);
	}

	@Override
	public Question updateTitle(int id, String title) {
		return questionDaoJpaImpl.viewQuestionById(id)
				.map(q->{
					q.setTitle(title);
					return questionDaoJpaImpl.updateQuestion(q);
				}).orElseThrow(QuestionNotFoundException::new);
	}

	@Override
	public Question updateDifficulty(int id, String difficulty) {
		return questionDaoJpaImpl.viewQuestionById(id)
				.map(q->{
					q.setDifficultyLevel(difficulty);
					return questionDaoJpaImpl.updateQuestion(q);
				}).orElseThrow(QuestionNotFoundException::new);
	}

	@Override
	public Question updateTag(int id, String tag) {
		return questionDaoJpaImpl.viewQuestionById(id)
				.map(q->{
					q.setTag(tag);
					return questionDaoJpaImpl.updateQuestion(q);
				}).orElseThrow(QuestionNotFoundException::new);
	}

	@Override
	public Question updateOptions(int id, int optionNo, String value) {
		return questionDaoJpaImpl.viewQuestionById(id)
				.map(q->{
					q.getOptions().set(optionNo - 1, value);
					return questionDaoJpaImpl.updateQuestion(q);
				}).orElseThrow(QuestionNotFoundException::new);
	}

	@Override
	public Question viewById(int id) {
		return questionDaoJpaImpl.viewQuestionById(id)
				.map(q->q)
				.orElseThrow(QuestionNotFoundException::new);
	}
}