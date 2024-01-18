package com.epam.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epam.customexception.QuizNotFoundException;
import com.epam.customexception.RedundantException;
import com.epam.database.QuizDao;
import com.epam.entity.Quiz;
@Service
public class QuizLibraryServicesImpl implements QuizLibraryServices {
	@Autowired
	private QuizDao quizDaoJpaImpl;
	@Override
	public Quiz addQuiz(Quiz quiz) {
		if (containsQuiz(quiz.getTitle())==0) {
			quizDaoJpaImpl.save(quiz);
		return quiz;
		}
		else
		{
			throw new RedundantException();
		}
	}

	private long containsQuiz(String title) {
		return quizDaoJpaImpl.loadAll().stream().filter(e->e.getTitle().equalsIgnoreCase(title)).count();
		
	}

	@Override
	public String deleteQuiz(int id) throws QuizNotFoundException {
		String result = "Quiz deleted successfully";
		if (quizDaoJpaImpl.load(id)!=null) {
			quizDaoJpaImpl.delete(id);
		} else {
			throw new QuizNotFoundException();
		}
		return result;
	}

	

	@Override
	public String updateQuiz(int id, String newTitle) throws QuizNotFoundException {
		String result = "Quiz updated successfully";
		if (quizDaoJpaImpl.load(id) != null) {
			Quiz updateQuiz=quizDaoJpaImpl.load(id);
			updateQuiz.setTitle(newTitle);
			quizDaoJpaImpl.update(updateQuiz);
		} else {
			throw new QuizNotFoundException();
		}
		return result;
	}

	@Override
	public List<Quiz> viewQuiz() {
		return quizDaoJpaImpl.loadAll();
	}

	@Override
	public Quiz viewQuizById(int id) throws QuizNotFoundException {
		Quiz quizById;
		if(quizDaoJpaImpl.load(id)!=null)
		{
			quizById = quizDaoJpaImpl.load(id);
		}
		else {
		throw new QuizNotFoundException();
		}
		return quizById;
	}
	
	

}
