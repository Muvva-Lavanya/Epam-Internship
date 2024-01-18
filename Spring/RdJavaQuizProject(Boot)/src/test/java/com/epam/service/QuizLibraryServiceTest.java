package com.epam.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.epam.customexception.QuizNotFoundException;
import com.epam.customexception.RedundantQuizException;
import com.epam.database.QuizDao;
import com.epam.entity.Question;
import com.epam.entity.Quiz;

@ExtendWith(MockitoExtension.class)
 class QuizLibraryServiceTest {
	@Mock
	QuizDao quizDaoJpaImpl;
	@InjectMocks
	QuizLibraryServiceImpl quizLibraryServicesImpl;
	List<Quiz> quizList=new ArrayList<>();
	Quiz quiz1=new Quiz();
	Quiz quiz2=new Quiz();
	@BeforeEach
	public void setUp() {
		Quiz quiz1=(new Quiz(1, "java", 2, 10,
				Arrays.asList(new Question("National bird of india", Arrays.asList("1", "2"), "Easy", "java", "1"),
						new Question("this for11 testings", Arrays.asList("1", "2"), "Easy", "java", "1")))); 
		quizList.add(quiz1); 
	}

	@Test
	void testCreateNewQuiz() {
		Quiz quiz2=(new Quiz(1, "python", 2, 10,
				Arrays.asList(new Question("National bird of india", Arrays.asList("1", "2"), "Easy", "java", "1"),
						new Question("this for11 testings", Arrays.asList("1", "2"), "Easy", "java", "1")))); 
		Mockito.when(quizDaoJpaImpl.viewAllQuizzes()).thenReturn(quizList);
		Mockito.when(quizDaoJpaImpl.saveQuiz(quiz2)).thenReturn(quiz2);
		Quiz savedQuiz = quizLibraryServicesImpl.addQuiz(quiz2);
		Mockito.verify(quizDaoJpaImpl).viewAllQuizzes();
		Mockito.verify(quizDaoJpaImpl).saveQuiz(quiz2);
		assertEquals(quiz2, savedQuiz); 
	}
	@Test
	void testCreateExistingQuiz() throws RedundantQuizException{
		Quiz quiz2=(new Quiz(1, "java", 2, 10,
				Arrays.asList(new Question("National bird of india", Arrays.asList("1", "2"), "Easy", "java", "1"),
						new Question("this for11 testings", Arrays.asList("1", "2"), "Easy", "java", "1")))); 
		Mockito.when(quizDaoJpaImpl.viewAllQuizzes()).thenReturn(quizList);
		assertThrows(RedundantQuizException.class,()->quizLibraryServicesImpl.addQuiz(quiz2));
		Mockito.verify(quizDaoJpaImpl).viewAllQuizzes(); 
		
	}

	@Test
	void testViewAllQuiz()
	{
		Mockito.when(quizDaoJpaImpl.viewAllQuizzes()).thenReturn(quizList);
		List<Quiz> retrivedQuizList=quizLibraryServicesImpl.viewQuiz();
		Mockito.verify(quizDaoJpaImpl).viewAllQuizzes();
		assertEquals(quizList,retrivedQuizList);
	}
	@Test
	void testViewQuizByExistingId()
	{
		Mockito.when(quizDaoJpaImpl.viewQuizById(0)).thenReturn(quizList.get(0));
		Quiz retrivedQuiz=quizLibraryServicesImpl.viewQuizById(0);
		Mockito.verify(quizDaoJpaImpl,times(2)).viewQuizById(0);
		assertEquals(quizList.get(0),retrivedQuiz);
	}
	@Test
	void testViewQuizByUnExistingId () throws QuizNotFoundException
	{
		Mockito.when(quizDaoJpaImpl.viewQuizById(20)).thenReturn(null);
		assertThrows(QuizNotFoundException.class,()->quizLibraryServicesImpl.viewQuizById(20));
		Mockito.verify(quizDaoJpaImpl).viewQuizById(20);	
		
		
		
	}
	@Test
	void deleteQuizByExistingId()
	{
		Quiz q=(new Quiz(1, "java", 2, 10,
				Arrays.asList(new Question("National bird of india", Arrays.asList("1", "2"), "Easy", "java", "1"),
						new Question("this for11 testings", Arrays.asList("1", "2"), "Easy", "java", "1"))));
		q.setId(2);
		Mockito.when(quizDaoJpaImpl.viewQuizById(2)).thenReturn(q);
		Mockito.doNothing().when(quizDaoJpaImpl).deleteQuiz(2);
		String message=quizLibraryServicesImpl.deleteQuiz(2);
		Mockito.verify(quizDaoJpaImpl).viewQuizById(2);
		Mockito.verify(quizDaoJpaImpl).deleteQuiz(2);
		assertEquals("Quiz deleted successfully",message);
		System.out.println(q);
		
	}
	@Test
	void deleteQuizByUnExistingId()
	{
		Mockito.when(quizDaoJpaImpl.viewQuizById(20)).thenReturn(null);
		assertThrows(QuizNotFoundException.class,()->quizLibraryServicesImpl.deleteQuiz(20));
		Mockito.verify(quizDaoJpaImpl).viewQuizById(20);
	
		
	}
	@Test
	void updateExistingQuiz()
	{
		Quiz q=(new Quiz(1, "java", 2, 10,
				Arrays.asList(new Question("National bird of india", Arrays.asList("1", "2"), "Easy", "java", "1"),
						new Question("this for11 testings", Arrays.asList("1", "2"), "Easy", "java", "1"))));
		q.setId(2);
		Mockito.when(quizDaoJpaImpl.viewQuizById(2)).thenReturn(q);
		q.setTitle("python");
		Mockito.when(quizDaoJpaImpl.updateQuiz(q)).thenReturn(q);
		String message=quizLibraryServicesImpl.updateQuiz(2,"python");
		Mockito.verify(quizDaoJpaImpl,times(2)).viewQuizById(2);
		Mockito.verify(quizDaoJpaImpl).updateQuiz(q);
		assertEquals("Quiz updated successfully", message);
		System.out.println(q);
	}
	@Test
	void updateUnExistingQuiz() throws QuizNotFoundException
	{
		Mockito.when(quizDaoJpaImpl.viewQuizById(20)).thenReturn(null);
		assertThrows(QuizNotFoundException.class,()->quizLibraryServicesImpl.updateQuiz(20,"sonarlint"));
		Mockito.verify(quizDaoJpaImpl).viewQuizById(20);
		
}
}
