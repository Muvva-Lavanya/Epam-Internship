package com.epam.database;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.epam.entity.Question;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
@Repository
public class QuestionDaoJpaImpl implements QuestionDao {
	EntityManagerFactory entityManagerFactory=Persistence.createEntityManagerFactory("jpa-db");
	EntityManager entityManager=entityManagerFactory.createEntityManager();

	@Override 
	public Question save(Question question) {
		entityManager.getTransaction().begin();
		entityManager.persist(question);
		entityManager.getTransaction().commit(); 
		return question;
	}

	@Override
	public Question load(int id) {
		return entityManager.find(Question.class, id);
	}

	@Override
	public void delete(int id) {
		Question question = entityManager.find(Question.class, id);
		entityManager.getTransaction().begin();
		entityManager.remove(question);
		entityManager.getTransaction().commit();

	}

	@Override
	public Question update(Question question) {
		entityManager.getTransaction().begin();
		entityManager.merge(question);
		entityManager.getTransaction().commit();
		return question;

	}

	@Override
	public List<Question> loadAll() {
		TypedQuery<Question> query = entityManager.createQuery("select q from Question q", Question.class);
		return query.getResultList();  
	}

}
