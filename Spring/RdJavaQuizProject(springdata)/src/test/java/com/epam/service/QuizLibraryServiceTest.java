package com.epam.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
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

import com.epam.customexception.QuizNotFoundException;
import com.epam.customexception.RedundantQuizException;
import com.epam.database.QuestionDao;
import com.epam.database.QuizDao;
import com.epam.entity.Question;
import com.epam.entity.Quiz;

@ExtendWith(MockitoExtension.class)
class QuizLibraryServiceTest {
	@Mock
	QuizDao quizDaoJpaImpl;
	@Mock
	QuestionDao questionDaoJpaImpl;
	@InjectMocks
	QuizLibraryServiceImpl quizLibraryServicesImpl;
	List<Quiz> quizList = new ArrayList<>();
	Quiz quiz = new Quiz();

	@BeforeEach
	public void setUp() {
		quiz = (new Quiz(1, "java", 2,
				Arrays.asList(new Question("National bird of india", Arrays.asList("1", "2"), "Easy", "java", "1"),
						new Question("this for11 testings", Arrays.asList("1", "2"), "Easy", "java", "1"))));
	} 
	@Test
	void testCreateQuiz() {
		Quiz quiz = new Quiz();
		quiz.setTitle("python");
		quiz.setMarks(2);
		Mockito.when(quizDaoJpaImpl.checkQuizExists(quiz)).thenReturn(Optional.empty());
		 List<Integer> questionIds = Arrays.asList(1, 2, 3);
		Question question1 = new Question();
		question1.setId(1);
		question1.setTitle("What is the capital of France?");
		Question question2 = new Question();
		question2.setId(2);
		question2.setTitle("What is the largest continent in the world?");
		Mockito.when(questionDaoJpaImpl.viewQuestionById(1)).thenReturn(Optional.ofNullable(question1));
		Mockito.when(questionDaoJpaImpl.viewQuestionById(2)).thenReturn(Optional.ofNullable(question2));
		Mockito.when(questionDaoJpaImpl.viewQuestionById(3)).thenReturn(Optional.empty());
		Mockito.when(quizDaoJpaImpl.saveQuiz(quiz)).thenReturn(quiz);
		Mockito.when(questionDaoJpaImpl.viewQuestionById(1)).thenReturn(Optional.empty());
		Quiz savedQuiz = quizLibraryServicesImpl.addQuiz(quiz, questionIds);
		Mockito.verify(quizDaoJpaImpl).checkQuizExists(quiz);
		Mockito.verify(quizDaoJpaImpl).saveQuiz(quiz);
		assertEquals(quiz, savedQuiz);
	}

	@Test
	void createDuplicateQuizTest() throws RedundantQuizException{
		quizList.add(quiz);
		Quiz quiz = quizList.get(0);
		List<Integer> ids=Arrays.asList(1);
		Mockito.when(quizDaoJpaImpl.checkQuizExists(quiz)).thenReturn(Optional.ofNullable(quiz));
		assertThrows(RedundantQuizException.class, () -> quizLibraryServicesImpl.addQuiz(quiz,ids));

	}

	@Test
	void testViewAllQuiz() {
		Mockito.when(quizDaoJpaImpl.viewAllQuizzes()).thenReturn(quizList);
		List<Quiz> retrivedQuizList = quizLibraryServicesImpl.viewQuiz();
		Mockito.verify(quizDaoJpaImpl).viewAllQuizzes();
		assertEquals(quizList, retrivedQuizList);
	}

	@Test
	void testViewQuizByExistingId() {
		quiz.setId(2);
		Mockito.when(quizDaoJpaImpl.viewQuizById(2)).thenReturn(Optional.ofNullable(quiz));
		Quiz retrivedQuiz = quizLibraryServicesImpl.viewQuizById(2);
		Mockito.verify(quizDaoJpaImpl).viewQuizById(2);
		assertEquals(quiz, retrivedQuiz);
	}

	@Test
	void testViewQuizByUnExistingId() throws QuizNotFoundException {
		Mockito.when(quizDaoJpaImpl.viewQuizById(20)).thenReturn(Optional.empty());
		assertThrows(QuizNotFoundException.class, () -> quizLibraryServicesImpl.viewQuizById(20));

	}

	@Test
	void deleteQuizByExistingId() {
		quiz.setId(2);
		Mockito.when(quizDaoJpaImpl.viewQuizById(2)).thenReturn(Optional.ofNullable(quiz));
		Mockito.when(quizDaoJpaImpl.deleteQuiz(2)).thenReturn(quiz);
		Quiz message = quizLibraryServicesImpl.deleteQuiz(2);
		Mockito.verify(quizDaoJpaImpl).viewQuizById(2);
		Mockito.verify(quizDaoJpaImpl).deleteQuiz(2);
		assertEquals(quiz, message);

	}

	@Test
	void deleteQuizByUnExistingId() {
		Mockito.when(quizDaoJpaImpl.viewQuizById(20)).thenReturn(Optional.empty());
		assertThrows(QuizNotFoundException.class, () -> quizLibraryServicesImpl.deleteQuiz(20));
		Mockito.verify(quizDaoJpaImpl).viewQuizById(20);

	}

	@Test
	void updateExistingQuiz() {
		quiz.setId(2);
		Mockito.when(quizDaoJpaImpl.viewQuizById(2)).thenReturn(Optional.ofNullable(quiz));
		quiz.setTitle("python");
		Mockito.when(quizDaoJpaImpl.updateQuiz(quiz)).thenReturn(quiz);
		Quiz message = quizLibraryServicesImpl.updateQuiz(2, "python");
		Mockito.verify(quizDaoJpaImpl).viewQuizById(2);
		Mockito.verify(quizDaoJpaImpl).updateQuiz(quiz);
		assertEquals(quiz, message);
	}

	@Test
	void updateUnExistingQuiz() throws QuizNotFoundException {
		Mockito.when(quizDaoJpaImpl.viewQuizById(20)).thenReturn(Optional.empty());
		assertThrows(QuizNotFoundException.class, () -> quizLibraryServicesImpl.updateQuiz(20, "sonarlint"));
		Mockito.verify(quizDaoJpaImpl).viewQuizById(20);

	}
}
