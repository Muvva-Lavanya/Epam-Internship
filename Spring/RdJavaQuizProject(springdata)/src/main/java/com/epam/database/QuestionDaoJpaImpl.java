package com.epam.database;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.epam.entity.Question;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;

@Repository
public class QuestionDaoJpaImpl implements QuestionDao {
	@Autowired
	EntityManager entityManager;

	@Override
	public Question saveQuestion(Question question) {
		entityManager.getTransaction().begin();
		entityManager.persist(question);
		entityManager.getTransaction().commit();
		return question;
	}

	@Override
	public Optional<Question> viewQuestionById(int id) {
		Question question = entityManager.find(Question.class, id);
		return Optional.ofNullable(question);
	}

	@Override
	public Question deleteQuestion(int id) {
		Question question = entityManager.find(Question.class, id);
			entityManager.getTransaction().begin();
			entityManager.remove(question);
			entityManager.getTransaction().commit();
			return question;
	}

	@Override
	public Question updateQuestion(Question question) {
		entityManager.getTransaction().begin();
		entityManager.merge(question);
		entityManager.getTransaction().commit();
		return question;
	}

	@Override
	public List<Question> viewAllQuestions() {
		TypedQuery<Question> query = entityManager.createQuery("select q from Question q", Question.class);
		return query.getResultList();
	}

	@Override
	public Optional<Question> checkQuestionExists(String questionTitle) {
		TypedQuery<Question> query = entityManager.createQuery("select q from Question q where q.title= :questionTitle",
				Question.class);
		query.setParameter("questionTitle", questionTitle);
		Optional<Question> question;
		try {
			question = Optional.ofNullable(query.getSingleResult());
		} catch (NoResultException e) {
			question = Optional.empty();
		}
		return question;
	}



}
