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

import com.epam.database.QuestionDaoJpaImpl;
import com.epam.entity.Question;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;

@ExtendWith(MockitoExtension.class)
class QuestionDaoImplTest {
	@Mock
	EntityManager entityManager;

	@Mock
	EntityTransaction transaction;

	@Mock
	TypedQuery<Question> query;

	@InjectMocks
	QuestionDaoJpaImpl questionDaoImpl;

	@Test
	void testSaveQuestion() {
		Question question = new Question("National bird of india", Arrays.asList("1", "2"), "Easy", "Test", "1");
		Mockito.when(entityManager.getTransaction()).thenReturn(transaction); 
		Mockito.doNothing().when(entityManager).persist(question);
		Question savedQuestion = questionDaoImpl.saveQuestion(question);
		Mockito.verify(entityManager, times(2)).getTransaction();
		assertEquals(question, savedQuestion);
	} 

	@Test
	void testDeleteQuestion() {
		Question question = new Question("National bird of india", Arrays.asList("1", "2"), "Easy", "Test", "1");
		question.setId(1);
		Mockito.when(entityManager.getTransaction()).thenReturn(transaction);
		Mockito.when(entityManager.find(Question.class, question.getId())).thenReturn(question);
		questionDaoImpl.deleteQuestion(question.getId()); 
		Mockito.verify(entityManager).find(Question.class, question.getId());
		Mockito.verify(entityManager).remove(question);
		Mockito.verify(entityManager, times(2)).getTransaction();
		Mockito.verify(entityManager.getTransaction()).begin();
		Mockito.verify(entityManager.getTransaction()).commit();

	}

	@Test
	void testviewAllQuestions() {
		Question question1 = new Question("National bird of india", Arrays.asList("1", "2"), "Easy", "Test", "1");
		Question question2 = new Question("this for11 testings", Arrays.asList("1", "2"), "Easy", "Test", "1");
		List<Question> questionsList = Arrays.asList(question1, question2);
		Mockito.when(entityManager.createQuery("select q from Question q", Question.class)).thenReturn(query);
		Mockito.when(query.getResultList()).thenReturn(questionsList);
		List<Question> retrivedUsers = questionDaoImpl.viewAllQuestions();
		Mockito.verify(entityManager).createQuery("select q from Question q", Question.class);
		Mockito.verify(query).getResultList();
		assertEquals(questionsList, retrivedUsers);
	}

	@Test
	void testViewQuestionById() {
		Question question = new Question("this for11 testings", Arrays.asList("1", "2"), "Easy", "Test", "1");
		question.setId(2);
		Mockito.when(entityManager.find(Question.class, question.getId())).thenReturn(question);
		Question retrivedQuestion = questionDaoImpl.viewQuestionById(question.getId());
		Mockito.verify(entityManager).find(Question.class, question.getId());
		assertEquals(question, retrivedQuestion);
	}

	@Test
	void testUpdateQuestion() {
		Question question1 = new Question("this for11 testings", Arrays.asList("1", "2"), "Easy", "Test", "1");
		Mockito.when(entityManager.getTransaction()).thenReturn(transaction);
		question1.setId(1);
		question1.setAnswer("2");
		Mockito.when(entityManager.merge(question1)).thenReturn(question1);
		Question updatedQuestion = questionDaoImpl.updateQuestion(question1);
		Mockito.verify(entityManager, times(2)).getTransaction();
		Mockito.verify(entityManager.getTransaction()).begin();
		Mockito.verify(entityManager.getTransaction()).commit();
		assertEquals(question1, updatedQuestion); 

	}
}
