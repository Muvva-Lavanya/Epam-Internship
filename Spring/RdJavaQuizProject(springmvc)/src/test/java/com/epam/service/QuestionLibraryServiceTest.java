package com.epam.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

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

import com.epam.customexception.QuestionNotFoundException;
import com.epam.customexception.RedundantQuestionException;
import com.epam.database.QuestionDao;
import com.epam.dto.QuestionDto;
import com.epam.entity.Question;

@ExtendWith(MockitoExtension.class)
class QuestionLibraryServiceTest {
	@Mock
	private QuestionDao questionDaoJpaImpl;
	@InjectMocks
	private QuestionLibraryServiceImpl questionLibraryServicesImpl;
	Question question;
	QuestionDto questionDto;
	@BeforeEach
	public void setUp() {
		questionDto=new QuestionDto("extension for java",Arrays.asList(".c",".java",".py"),"Medium","java","8");
		question=new Question(questionDto);
	}
 
	@Test
	void testCreateQuestion() throws RedundantQuestionException {
		Mockito.when(questionDaoJpaImpl.checkQuestionExists(question.getTitle())).thenReturn(Optional.empty());
		Mockito.when(questionDaoJpaImpl.saveQuestion(question)).thenReturn(question);
		Question savedQuestion=questionLibraryServicesImpl.createQuestion(question);
		Mockito.verify(questionDaoJpaImpl).checkQuestionExists(question.getTitle());
		Mockito.verify(questionDaoJpaImpl).saveQuestion(question);
		assertEquals(question, savedQuestion);
	}
	
	
	@Test
	void testCreateExistingQuestion() throws RedundantQuestionException {
		Mockito.when(questionDaoJpaImpl.checkQuestionExists("extension for java")).thenReturn(Optional.ofNullable(question));
		assertThrows(RedundantQuestionException.class,()->questionLibraryServicesImpl.createQuestion(question));
	}
 
	

	@Test
	void testdeleteExistingQuestion() throws QuestionNotFoundException {
		question.setId(1);
		Mockito.when(questionDaoJpaImpl.viewQuestionById(1)).thenReturn(Optional.ofNullable(question));
		Mockito.when(questionDaoJpaImpl.deleteQuestion(1)).thenReturn(question);
		Question result = questionLibraryServicesImpl.deleteQuestion(1);
		Mockito.verify(questionDaoJpaImpl).viewQuestionById(1);
		Mockito.verify(questionDaoJpaImpl).deleteQuestion(1);
		assertEquals(question, result);
	}

	@Test
	void testdeleteUnExistingQuestion() throws QuestionNotFoundException {
		Mockito.when(questionDaoJpaImpl.viewQuestionById(1000)).thenReturn(Optional.empty());
		assertThrows(QuestionNotFoundException.class,()->questionLibraryServicesImpl.deleteQuestion(1000));
		Mockito.verify(questionDaoJpaImpl).viewQuestionById(1000);
		
	}

	@Test 
	void testViewAllQuestions() {
		List<Question> questionsList = Arrays.asList(question);
		Mockito.when(questionDaoJpaImpl.viewAllQuestions()).thenReturn(questionsList);
		List<Question> retrivedQuestions = questionLibraryServicesImpl.viewQuestion();
		Mockito.verify(questionDaoJpaImpl).viewAllQuestions();
		System.out.println(questionsList+" "+retrivedQuestions);
		assertEquals(questionsList, retrivedQuestions);
	}

	@Test
	void viewExistingQuestionById() throws QuestionNotFoundException {
		question.setId(2);
		Mockito.when(questionDaoJpaImpl.viewQuestionById(2)).thenReturn(Optional.ofNullable(question));
		Question retrivedQuestion = questionLibraryServicesImpl.viewById(2);
		Mockito.verify(questionDaoJpaImpl).viewQuestionById(2);
		assertEquals(question, retrivedQuestion);
	}

	@Test
	void viewUnExistingQuestionById() {
		Mockito.when(questionDaoJpaImpl.viewQuestionById(1000)).thenReturn(Optional.empty());
		assertThrows(QuestionNotFoundException.class,()->questionLibraryServicesImpl.viewById(1000));
		Mockito.verify(questionDaoJpaImpl).viewQuestionById(1000);
		
	} 

	@Test
	void updateTagForExistingQuestion() {
		question.setId(2);
		Mockito.when(questionDaoJpaImpl.viewQuestionById(2)).thenReturn(Optional.ofNullable(question));
		question.setTag("java");
		Mockito.when(questionDaoJpaImpl.updateQuestion(question)).thenReturn(question);
		Question message = questionLibraryServicesImpl.updateTag(2, "java");
		Mockito.verify(questionDaoJpaImpl).viewQuestionById(2);
		Mockito.verify(questionDaoJpaImpl).updateQuestion(question);
		assertEquals(question, message); 
	}

	@Test
	void updateTagForUnExistingQuestion() throws QuestionNotFoundException {
		Mockito.when(questionDaoJpaImpl.viewQuestionById(1000)).thenReturn(Optional.empty());
		assertThrows(QuestionNotFoundException.class,()->questionLibraryServicesImpl.updateTag(1000,"hello world"));
		Mockito.verify(questionDaoJpaImpl).viewQuestionById(1000);
		
	}

	@Test
	void updateDifficultyLevelForExistingQueztion() {
		question.setId(2);
		Mockito.when(questionDaoJpaImpl.viewQuestionById(2)).thenReturn(Optional.ofNullable(question));
		question.setDifficultyLevel("Easy");
		Mockito.when(questionDaoJpaImpl.updateQuestion(question)).thenReturn(question);
		Question message = questionLibraryServicesImpl.updateDifficulty(2, "Easy");
		Mockito.verify(questionDaoJpaImpl).viewQuestionById(2);
		Mockito.verify(questionDaoJpaImpl).updateQuestion(question);
		assertEquals(question, message);
	}


	@Test
	void updateDifficultyLevelForUnExistingQuestion() throws QuestionNotFoundException {
		Mockito.when(questionDaoJpaImpl.viewQuestionById(1000)).thenReturn(Optional.empty());
		assertThrows(QuestionNotFoundException.class,()->questionLibraryServicesImpl.updateDifficulty(1000,"Easy"));
		Mockito.verify(questionDaoJpaImpl).viewQuestionById(1000);
		
	}

	@Test
	void updateOptionsForExistingQuestion() {
		question.setId(2);
		Mockito.when(questionDaoJpaImpl.viewQuestionById(2)).thenReturn(Optional.ofNullable(question));
		Mockito.when(questionDaoJpaImpl.updateQuestion(question)).thenReturn(question);
		question.getOptions().set(1, "1");
		Question message = questionLibraryServicesImpl.updateOptions(2, 1, "1");
		Mockito.verify(questionDaoJpaImpl).viewQuestionById(2);
		assertEquals(question, message);
	}

	@Test
	void updateOptionForUnExistingQuestion() throws QuestionNotFoundException {
		Mockito.when(questionDaoJpaImpl.viewQuestionById(1000)).thenReturn(Optional.empty());
		assertThrows(QuestionNotFoundException.class,()->questionLibraryServicesImpl.updateOptions(1000, 1,"1"));
		Mockito.verify(questionDaoJpaImpl).viewQuestionById(1000);
		
	}

	@Test
	void updateAnswerForExistingQuestion() {
		question.setId(2);
		Mockito.when(questionDaoJpaImpl.viewQuestionById(2)).thenReturn(Optional.ofNullable(question));
		Mockito.when(questionDaoJpaImpl.updateQuestion(question)).thenReturn(question);
		Question message = questionLibraryServicesImpl.updateAnswer(2,".c");
		question.setAnswer("1");
		Mockito.verify(questionDaoJpaImpl).viewQuestionById(2);
		assertEquals(question, message);

	}
	@Test
	void updateAnswerForUnExistingQuestion() {
		Mockito.when(questionDaoJpaImpl.viewQuestionById(1000)).thenReturn(Optional.empty());
		assertThrows(QuestionNotFoundException.class,()->questionLibraryServicesImpl.updateAnswer(1000,"hi"));
		Mockito.verify(questionDaoJpaImpl).viewQuestionById(1000);
	}
	@Test
	void updateTitleForExistingQuestion() {
		question.setId(2);
		Mockito.when(questionDaoJpaImpl.viewQuestionById(2)).thenReturn(Optional.ofNullable(question));
		question.setTitle("hello world");
		Mockito.when(questionDaoJpaImpl.updateQuestion(question)).thenReturn(question);
		Question message=questionLibraryServicesImpl.updateTitle(2,"hello world");
		Mockito.verify(questionDaoJpaImpl).viewQuestionById(2);
		Mockito.verify(questionDaoJpaImpl).updateQuestion(question);
		assertEquals(question, message);
	}

	@Test
	void updateTitleForUnExistingQuestion() {
		Mockito.when(questionDaoJpaImpl.viewQuestionById(1000)).thenReturn(Optional.empty());
		assertThrows(QuestionNotFoundException.class,()->questionLibraryServicesImpl.updateTitle(1000,"hello world"));
		Mockito.verify(questionDaoJpaImpl).viewQuestionById(1000);
		
	} 
}
