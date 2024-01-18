package com.epam.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.epam.customexception.DuplicateQuizException;
import com.epam.customexception.QuizException;
import com.epam.dto.QuizDto;
import com.epam.entity.Question;
import com.epam.entity.Quiz;
import com.epam.service.QuestionLibraryServiceImpl;
import com.epam.service.QuizLibraryServiceImpl;
@WebMvcTest(QuizLibraryController.class)
class QuizLibraryControllerTest {
	@MockBean
	private QuizLibraryServiceImpl quizLibraryServiceImpl;
	@MockBean
	private QuestionLibraryServiceImpl questionLibraryServiceImpl;
	@Autowired
	private MockMvc mockMvc;
	Quiz quiz;
	Question question;
	QuizDto quizDto;
	@BeforeEach
	public void setUp()
	{
		question = new Question("extension for java", Arrays.asList(".c", ".java", ".py"), "Medium", "java",
				"8");
		quiz=new Quiz(1,"java",2,Arrays.asList(question)); 
		quizDto=new QuizDto(1,"java",2,Arrays.asList(question));
	} 
	@Test
	 void testDeleteExistingQuiz() throws Exception
	{
		Mockito.when(quizLibraryServiceImpl.deleteQuiz(1)).thenReturn(quizDto);
		mockMvc.perform(get("/deleteQuiz").param("id", "1")).andExpect(status().isOk())
		.andExpect(view().name("quizLibraryOperationStatus.jsp")).andExpect(model().attributeExists("message"))
		.andExpect(model().attribute("message", "Quiz with id: 1 deleted successfully")).andReturn();
	}
	
	@Test
	void testDeleteUnExistingQuiz() throws Exception
	{
		Mockito.when(quizLibraryServiceImpl.deleteQuiz(100)).thenThrow(new QuizException("Quiz doesnt exist"));
		mockMvc.perform(get("/deleteQuiz").param("id", "100")).andExpect(status().isOk())
		.andExpect(view().name("quizLibraryOperationStatus.jsp")).andExpect(model().attributeExists("message"))
		.andExpect(model().attribute("message", "Quiz doesnt exist")).andReturn();
	
	}

	@Test
	void testUpdateExistingQuiz() throws Exception
	{
		question.setId(1); 
		List<Integer> questionIds=Arrays.asList(1); 
		Mockito.when(quizLibraryServiceImpl.updateQuiz(any(),eq(questionIds))).thenReturn(quizDto);
		mockMvc.perform(post("/updateQuiz").param("questionIds","1")).andExpect(status().isOk())
		.andExpect(view().name("quizLibraryOperationStatus.jsp")).andExpect(model().attributeExists("message"))
		.andExpect(model().attribute("message", "Quiz with id: 1"
				+ " updated successfully")).andReturn();
	
	}
	
	@Test
	void testUpdateQuizExistingTitle() throws Exception
	{
		List<Integer> questionIds=Arrays.asList(1);
		Mockito.when(quizLibraryServiceImpl.updateQuiz(any(), eq(questionIds))).thenThrow(new DuplicateQuizException("Quiz already exists"));
		mockMvc.perform(post("/updateQuiz").param("questionIds","1")).andExpect(status().isOk())
		.andExpect(view().name("quizLibraryOperationStatus.jsp")).andExpect(model().attributeExists("message"))
		.andExpect(model().attribute("message", "Quiz already exists")).andReturn();
	
	}


	@Test
	void testViewQuizzes() throws Exception
	{
		List<Quiz> quizList=Arrays.asList(quiz);
		Mockito.when(quizLibraryServiceImpl.getAllQuizzes()).thenReturn(quizList);
		mockMvc.perform(get("/viewAllQuizzes")).andExpect(status().isOk())
		.andExpect(view().name("viewQuizzes.jsp")).andExpect(model().attributeExists("quizList"))
		.andExpect(model().attribute("quizList",quizList)).andReturn();
	
		
	}
	
	@Test
	void testEmptyViewQuizzes() throws Exception
	{
		List<Quiz> quizList=List.of();
		Mockito.when(quizLibraryServiceImpl.getAllQuizzes()).thenReturn(quizList);
		mockMvc.perform(get("/viewAllQuizzes")).andExpect(status().isOk())
		.andExpect(view().name("viewQuizzes.jsp")).andExpect(model().attributeExists("message"))
		.andExpect(model().attribute("message","There are No Quizzes to display")).andReturn();
	}
	
	@Test
	void testCreateQuiz() throws Exception
	{
		question.setId(1); 
		List<Integer> questionIds=Arrays.asList(1); 
		Mockito.when(quizLibraryServiceImpl.addQuiz(any(),eq(questionIds))).thenReturn(quizDto);
		mockMvc.perform(post("/createQuiz").param("questionIdList","1")).andExpect(status().isOk())
		.andExpect(view().name("quizLibraryOperationStatus.jsp")).andExpect(model().attributeExists("message"))
		.andExpect(model().attribute("message", "Quiz with id: 1 created successfully")).andReturn();
	
	}
	
	@Test
	void testCreateExistingQuiz() throws Exception
	{
		List<Integer> questionIds=Arrays.asList(1);
		Mockito.when(quizLibraryServiceImpl.addQuiz(any(), eq(questionIds))).thenThrow(new DuplicateQuizException("Quiz already exists"));
		mockMvc.perform(post("/createQuiz").param("title", "java").param("marks","2").param("questionIdList","1")).andExpect(status().isOk())
		.andExpect(view().name("quizLibraryOperationStatus.jsp")).andExpect(model().attributeExists("message"))
		.andExpect(model().attribute("message", "Quiz already exists")).andReturn();
	
	}
	
	@Test
	void testViewQuizById() throws Exception
	{
		quiz.setId(1);
		Mockito.when(quizLibraryServiceImpl.getQuizById(1)).thenReturn(quiz);
		mockMvc.perform(get("/viewQuiz").param("id","1")).andExpect(status().isOk())
		.andExpect(view().name("quizUpdation.jsp")).andExpect(model().attributeExists("quiz"))
		.andExpect(model().attribute("quiz",quiz)).andReturn();
	
		
	}
	
	@Test
	void testQuizQuestions() throws Exception
	{
		quiz.setId(1);
		List<Question> questionList=Arrays.asList(question);
		Mockito.when(quizLibraryServiceImpl.getQuizById(1)).thenReturn(quiz);
		mockMvc.perform(get("/quizQuestions").param("id","1")).andExpect(status().isOk()).andExpect(view().name("quizQuestions.jsp"))
			.andExpect(model().attributeExists("questionList")).andExpect(model().attribute("questionList",questionList)).andReturn();
	}
}
