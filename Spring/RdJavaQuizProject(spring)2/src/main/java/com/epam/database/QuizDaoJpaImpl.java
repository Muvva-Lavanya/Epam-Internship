package com.epam.database;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.epam.entity.Quiz;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

@Repository
public class QuizDaoJpaImpl implements QuizDao {
	EntityManagerFactory entityManagerFactory=Persistence.createEntityManagerFactory("jpa-db");;
	EntityManager entityManager=entityManagerFactory.createEntityManager();


	@Override
	public Quiz save(Quiz quiz) {
		entityManager.getTransaction().begin();
		entityManager.persist(quiz);
		entityManager.getTransaction().commit();
		return quiz;

	}

	@Override
	public Quiz load(int id) {
		return entityManager.find(Quiz.class, id);
	}

	@Override
	public void delete(int id) {
		Quiz deleteQuiz = entityManager.find(Quiz.class, id);
		entityManager.getTransaction().begin();
		entityManager.remove(deleteQuiz);
		entityManager.getTransaction().commit();

	}

	@Override
	public Quiz update(Quiz updateQuiz) {
		entityManager.getTransaction().begin();
		entityManager.merge(updateQuiz);
		entityManager.getTransaction().commit();
		return updateQuiz;

	}

	@Override
	public List<Quiz> loadAll() {
		TypedQuery<Quiz> query = entityManager.createQuery("select q from Quiz q", Quiz.class);
		return query.getResultList();
	}


}
