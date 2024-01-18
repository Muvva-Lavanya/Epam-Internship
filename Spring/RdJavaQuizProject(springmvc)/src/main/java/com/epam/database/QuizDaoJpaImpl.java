package com.epam.database;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.epam.customexception.QuizNotFoundException;
import com.epam.entity.Quiz;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;

@Repository
public class QuizDaoJpaImpl implements QuizDao {
	@Autowired
	EntityManager entityManager;

	@Override
	public Quiz saveQuiz(Quiz quiz) {
		entityManager.getTransaction().begin();
		entityManager.persist(quiz);
		entityManager.getTransaction().commit();
		return quiz;

	}

	@Override
	public Optional<Quiz> viewQuizById(int id) {
		Quiz quiz = entityManager.find(Quiz.class, id); 
		return Optional.ofNullable(quiz);
	}

	@Override
	public Quiz deleteQuiz(int id) throws QuizNotFoundException {
		Quiz quiz = entityManager.find(Quiz.class, id);
			entityManager.getTransaction().begin();
			entityManager.remove(quiz);
			entityManager.getTransaction().commit();
			return quiz;
	}

	@Override
	public Quiz updateQuiz(Quiz updateQuiz) {
		entityManager.getTransaction().begin();
		entityManager.merge(updateQuiz);
		entityManager.getTransaction().commit();
		return updateQuiz;

	}

	@Override
	public List<Quiz> viewAllQuizzes() {
		TypedQuery<Quiz> query = entityManager.createQuery("select q from Quiz q", Quiz.class);
		return query.getResultList();
	}

	@Override
	public Optional<Quiz> checkQuizExists(Quiz quiz) {
		TypedQuery<Quiz> query = entityManager.createQuery("select q from Quiz q where q.title= :quizTitle",
				Quiz.class);
		query.setParameter("quizTitle", quiz.getTitle());
		Optional<Quiz> checkQuiz;
		try {
			checkQuiz= Optional.ofNullable(query.getSingleResult());
		}
		catch(NoResultException e)
		{
			checkQuiz=Optional.empty();
		}
		return checkQuiz;

	}

}
