package com.epam.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.epam.customexception.QuizNotFoundException;
import com.epam.customexception.RedundantQuizException;
import com.epam.database.QuizDao;
import com.epam.entity.Quiz;

@Service
public class QuizLibraryServiceImpl implements QuizLibraryService {
	@Autowired
	@Qualifier(value = "quizDaoJpaImpl")
	private QuizDao quizDaoJpaImpl;

	@Override
	public Quiz addQuiz(Quiz quiz) {
		if (quizDaoJpaImpl.checkQuizExists(quiz).isEmpty()) {
			quizDaoJpaImpl.saveQuiz(quiz);
			return quiz; 
		} else {
			throw new RedundantQuizException();
		} 
	}

	@Override
	public Quiz deleteQuiz(int id) throws QuizNotFoundException {
		return quizDaoJpaImpl.deleteQuiz(id);
	}

	@Override
	public Quiz updateQuiz(int id, String newTitle) throws QuizNotFoundException {
		Quiz quiz=quizDaoJpaImpl.viewQuizById(id);
		return Optional.ofNullable(quiz).map(updatequiz->{
			updatequiz.setTitle(newTitle);
			return quizDaoJpaImpl.updateQuiz(updatequiz);
		}).orElseThrow(QuizNotFoundException::new); 
	}

	@Override
	public List<Quiz> viewQuiz() {
		return quizDaoJpaImpl.viewAllQuizzes();
	}

	@Override
	public Quiz viewQuizById(int id) throws QuizNotFoundException {
		return quizDaoJpaImpl.viewQuizById(id);
	}

}
