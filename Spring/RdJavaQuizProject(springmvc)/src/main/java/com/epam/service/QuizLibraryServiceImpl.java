package com.epam.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import com.epam.customexception.QuizNotFoundException;
import com.epam.customexception.RedundantQuizException;
import com.epam.database.QuestionDao;
import com.epam.database.QuizDao;
import com.epam.entity.Question;
import com.epam.entity.Quiz;

@Service
public class QuizLibraryServiceImpl implements QuizLibraryService {
	@Autowired
	@Qualifier(value = "quizDaoJpaImpl")
	private QuizDao quizDaoJpaImpl;
	@Autowired
	@Qualifier(value = "questionDaoJpaImpl")
	private QuestionDao questionDaoJpaImpl;

	@Override
	public Quiz addQuiz(Quiz quiz, List<Integer> questionIds) {
		if (quizDaoJpaImpl.checkQuizExists(quiz).isEmpty()) {
			List<Question> questionsList=questionIds.stream().filter(id -> questionDaoJpaImpl.viewQuestionById(id).isPresent())
					.map(id -> questionDaoJpaImpl.viewQuestionById(id).get()).toList();
			quiz.setQuestion(questionsList);
			quizDaoJpaImpl.saveQuiz(quiz);
			
			return quiz;
		} else {
			throw new RedundantQuizException(); 
		}
	}

	@Override
	public Quiz deleteQuiz(int id) throws QuizNotFoundException {
		return quizDaoJpaImpl.viewQuizById(id).map(q -> quizDaoJpaImpl.deleteQuiz(id))
				.orElseThrow(QuizNotFoundException::new);

	}

	@Override
	public Quiz updateQuiz(int id, String newTitle) throws QuizNotFoundException {
		return quizDaoJpaImpl.viewQuizById(id).map(updatequiz -> {
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
		return quizDaoJpaImpl.viewQuizById(id).map(q -> q).orElseThrow(QuizNotFoundException::new);
	}

}
