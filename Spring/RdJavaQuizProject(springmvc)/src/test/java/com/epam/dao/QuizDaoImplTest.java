package com.epam.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
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
import jakarta.persistence.NoResultException;
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
	Quiz quiz=new Quiz();
	@BeforeEach
	public void setUp()
	{
		quiz=(new Quiz(1, "java", 2,
				Arrays.asList(new Question("National bird of india", Arrays.asList("1", "2"), "Easy", "java", "1"),
						new Question("this for11 testings", Arrays.asList("1", "2"), "Easy", "java", "1"))));
	}
	@Test
	void testSaveQuiz()
	{
		
		quiz.setId(2);
		Mockito.when(entityManager.getTransaction()).thenReturn(transaction);
		Mockito.doNothing().when(entityManager).persist(quiz);
		Quiz savedQuiz=quizDaoJpaImpl.saveQuiz(quiz);
		Mockito.verify(entityManager, times(2)).getTransaction();
		Mockito.verify(entityManager).persist(quiz);
		assertEquals(quiz, savedQuiz);
	} 
	@Test
	void testDeleteQuiz()
	{
		quiz.setId(2);
		Mockito.when(entityManager.getTransaction()).thenReturn(transaction);
		Mockito.when(entityManager.find(Quiz.class, quiz.getId())).thenReturn(quiz);
		quizDaoJpaImpl.deleteQuiz(quiz.getId()); 
		Mockito.verify(entityManager).find(Quiz.class, quiz.getId());
		Mockito.verify(entityManager).remove(quiz);
		Mockito.verify(entityManager, times(2)).getTransaction();
		Mockito.verify(entityManager.getTransaction()).begin();
		Mockito.verify(entityManager.getTransaction()).commit();
	}
	@Test
	void testViewQuizById() {
		quiz.setId(2);
		Mockito.when(entityManager.find(Quiz.class, quiz.getId())).thenReturn(quiz);
		Optional<Quiz> retrivedQuiz = quizDaoJpaImpl.viewQuizById(quiz.getId());
		Mockito.verify(entityManager).find(Quiz.class, quiz.getId());
		assertEquals(quiz, retrivedQuiz.get());
	}
	@Test
	void testViewUnExistingQuizById() {
		Mockito.when(entityManager.find(Quiz.class, 100)).thenReturn(null);
		Optional<Quiz> retrivedQuiz = quizDaoJpaImpl.viewQuizById(100);
		Mockito.verify(entityManager).find(Quiz.class,100);
		assertTrue(retrivedQuiz.isEmpty());
	}
	@Test
	void testviewAllQuiz() {
		Quiz q=(new Quiz(1, "java", 2,
				Arrays.asList(new Question("National bird of india", Arrays.asList("1", "2"), "Easy", "java", "1"),
						new Question("this for11 testings", Arrays.asList("1", "2"), "Easy", "java", "1"))));
		Quiz q1=(new Quiz(1, "python", 2,
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
	@Test
	void checkExistingQuizExists()
	{
		
		Mockito.when(entityManager.createQuery("select q from Quiz q where q.title= :quizTitle",
				Quiz.class)).thenReturn(query);
		Mockito.when(query.getSingleResult()).thenReturn(quiz);
		Optional<Quiz> checkQuiz=quizDaoJpaImpl.checkQuizExists(quiz);
		Mockito.verify(entityManager).createQuery("select q from Quiz q where q.title= :quizTitle",
				Quiz.class);
		Mockito.verify(query).getSingleResult();
		assertEquals(quiz,checkQuiz.get());
	}
	
	@Test
	void checkUnExistingQuizExists()
	{
		Quiz quiz1=quiz=(new Quiz(1, "java1", 2,
				Arrays.asList(new Question("National bird of india", Arrays.asList("1", "2"), "Easy", "java", "1"),
						new Question("this for11 testings", Arrays.asList("1", "2"), "Easy", "java", "1"))));
		Mockito.when(entityManager.createQuery("select q from Quiz q where q.title= :quizTitle",
				Quiz.class)).thenReturn(query);
		Mockito.when(query.getSingleResult()).thenThrow(new NoResultException());
		Optional<Quiz> checkQuiz=quizDaoJpaImpl.checkQuizExists(quiz1);
		Mockito.verify(entityManager).createQuery("select q from Quiz q where q.title= :quizTitle",
				Quiz.class);
		Mockito.verify(query).getSingleResult();
		assertTrue(checkQuiz.isEmpty());
	}
}
