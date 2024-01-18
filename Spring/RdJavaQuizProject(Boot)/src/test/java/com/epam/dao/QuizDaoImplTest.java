package com.epam.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.epam.database.QuizDaoJpaImpl;
import com.epam.entity.Question;
import com.epam.entity.Quiz;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;

@ExtendWith(MockitoExtension.class)
 class QuizDaoImplTest {
	@Mock
	EntityManager entityManager;
	
	@Mock
	EntityTransaction transaction;
	@Mock
	TypedQuery<Quiz> query;
	@InjectMocks
	QuizDaoJpaImpl quizDaoJpaImpl;
	
	@Test
	void testSaveQuiz()
	{
		Quiz q=(new Quiz(1, "java", 2, 10,
				Arrays.asList(new Question("National bird of india", Arrays.asList("1", "2"), "Easy", "java", "1"),
						new Question("this for11 testings", Arrays.asList("1", "2"), "Easy", "java", "1"))));
		q.setId(2);
		Mockito.when(entityManager.getTransaction()).thenReturn(transaction);
		Mockito.doNothing().when(entityManager).persist(q);
		Quiz savedQuiz=quizDaoJpaImpl.saveQuiz(q);
		Mockito.verify(entityManager, times(2)).getTransaction();
		Mockito.verify(entityManager).persist(q);
		assertEquals(q, savedQuiz);
	} 
	@Test
	void testDeleteQuiz()
	{
		Quiz q=(new Quiz(1, "java", 2, 10,
				Arrays.asList(new Question("National bird of india", Arrays.asList("1", "2"), "Easy", "java", "1"),
						new Question("this for11 testings", Arrays.asList("1", "2"), "Easy", "java", "1"))));
		q.setId(2);
		Mockito.when(entityManager.getTransaction()).thenReturn(transaction);
		Mockito.when(entityManager.find(Quiz.class, q.getId())).thenReturn(q);
		quizDaoJpaImpl.deleteQuiz(q.getId()); 
		Mockito.verify(entityManager).find(Quiz.class, q.getId());
		Mockito.verify(entityManager).remove(q);
		Mockito.verify(entityManager, times(2)).getTransaction();
		Mockito.verify(entityManager.getTransaction()).begin();
		Mockito.verify(entityManager.getTransaction()).commit();
	}
	@Test
	void testViewQuizById() {
		Quiz q=(new Quiz(1, "java", 2, 10,
				Arrays.asList(new Question("National bird of india", Arrays.asList("1", "2"), "Easy", "java", "1"),
						new Question("this for11 testings", Arrays.asList("1", "2"), "Easy", "java", "1"))));
		q.setId(2);
		Mockito.when(entityManager.find(Quiz.class, q.getId())).thenReturn(q);
		Quiz retrivedQuiz = quizDaoJpaImpl.viewQuizById(q.getId());
		Mockito.verify(entityManager).find(Quiz.class, q.getId());
		assertEquals(q, retrivedQuiz);
	}
	@Test
	void testviewAllQuiz() {
		Quiz q=(new Quiz(1, "java", 2, 10,
				Arrays.asList(new Question("National bird of india", Arrays.asList("1", "2"), "Easy", "java", "1"),
						new Question("this for11 testings", Arrays.asList("1", "2"), "Easy", "java", "1"))));
		Quiz q1=(new Quiz(1, "python", 2, 10,
				Arrays.asList(new Question("National bird of india", Arrays.asList("1", "2"), "Easy", "java", "1"),
						new Question("this for11 testings", Arrays.asList("1", "2"), "Easy", "java", "1"))));
		List<Quiz> quizList=Arrays.asList(q,q1);
		Mockito.when(entityManager.createQuery("select q from Quiz q", Quiz.class)).thenReturn(query);
		Mockito.when(query.getResultList()).thenReturn(quizList);
		List<Quiz> retrivedQuizes = quizDaoJpaImpl.viewAllQuizzes();
		Mockito.verify(entityManager).createQuery("select q from Quiz q", Quiz.class);
		Mockito.verify(query).getResultList();
		assertEquals(quizList, retrivedQuizes);
	}
	

	@Test
	void testUpdateQuiz() {
		Quiz quiz=(new Quiz(1, "java", 2, 10,
				Arrays.asList(new Question("National bird of india", Arrays.asList("1", "2"), "Easy", "java", "1"),
						new Question("this for11 testings", Arrays.asList("1", "2"), "Easy", "java", "1"))));
		quiz.setId(2);
		Mockito.when(entityManager.getTransaction()).thenReturn(transaction);
		quiz.setId(1);
		quiz.setTitle("c");
		Mockito.when(entityManager.merge(quiz)).thenReturn(quiz);
		Quiz updatedQuiz = quizDaoJpaImpl.updateQuiz(quiz);
		Mockito.verify(entityManager, times(2)).getTransaction();
		Mockito.verify(entityManager.getTransaction()).begin();
		Mockito.verify(entityManager.getTransaction()).commit();
		assertEquals(quiz, updatedQuiz); 

	}

}
