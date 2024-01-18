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
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.epam.customexception.QuizNotFoundException;
import com.epam.customexception.RedundantQuizException;
import com.epam.entity.Question;
import com.epam.entity.Quiz;
import com.epam.service.QuizLibraryServiceImpl;
@ExtendWith(MockitoExtension.class)
class QuizLibraryControllerTest {
	@Mock
	private QuizLibraryServiceImpl quizLibraryServiceImpl;
	@InjectMocks
	private QuizLibraryController quizLibraryController;
	
	private MockMvc mockMvc;
	Quiz quiz;
	Question question;
	@BeforeEach
	public void setUp()
	{
		mockMvc = MockMvcBuilders.standaloneSetup(quizLibraryController).build();
		question = new Question("extension for java", Arrays.asList(".c", ".java", ".py"), "Medium", "java",
				"8");
		quiz=new Quiz(1,"java",2,Arrays.asList(question));
	}
	@Test
	 void testDeleteExistingQuiz() throws Exception
	{
		Mockito.when(quizLibraryServiceImpl.deleteQuiz(1)).thenReturn(quiz);
		mockMvc.perform(post("/deleteQuiz").param("id", "1")).andExpect(status().isOk())
		.andExpect(view().name("quizLibraryOperationStatus.jsp")).andExpect(model().attributeExists("message"))
		.andExpect(model().attribute("message", "Quiz with id: 1 deleted successfully")).andReturn();
	}
	
	@Test
	void testDeleteUnExistingQuiz() throws Exception
	{
		Mockito.when(quizLibraryServiceImpl.deleteQuiz(100)).thenThrow(new QuizNotFoundException());
		mockMvc.perform(post("/deleteQuiz").param("id", "100")).andExpect(status().isOk())
		.andExpect(view().name("quizLibraryOperationStatus.jsp")).andExpect(model().attributeExists("message"))
		.andExpect(model().attribute("message", "Quiz doesnt exist")).andReturn();
	
	}
	
	@Test
	void testUpdateExistingQuiz() throws Exception
	{
		Mockito.when(quizLibraryServiceImpl.updateQuiz(1,"Java")).thenReturn(quiz);
		mockMvc.perform(post("/updateQuiz").param("id", "1").param("title","Java")).andExpect(status().isOk())
		.andExpect(view().name("quizLibraryOperationStatus.jsp")).andExpect(model().attributeExists("message"))
		.andExpect(model().attribute("message", "Quiz title with id: 1 updated successfully")).andReturn();
	
	}
	
	@Test
	void testUpdateUnExistingQuiz() throws Exception
	{
		Mockito.when(quizLibraryServiceImpl.updateQuiz(100,"java")).thenThrow(new QuizNotFoundException());
		mockMvc.perform(post("/updateQuiz").param("id", "100").param("title","java")).andExpect(status().isOk())
		.andExpect(view().name("quizLibraryOperationStatus.jsp")).andExpect(model().attributeExists("message"))
		.andExpect(model().attribute("message", "Quiz doesnt exist")).andReturn();
	
	}
	
	@Test
	void testViewQuizzes() throws Exception
	{
		List<Quiz> quizList=Arrays.asList(quiz);
		Mockito.when(quizLibraryServiceImpl.viewQuiz()).thenReturn(quizList);
		mockMvc.perform(get("/viewAllQuizzes")).andExpect(status().isOk())
		.andExpect(view().name("viewQuizzes.jsp")).andExpect(model().attributeExists("quizList"))
		.andExpect(model().attribute("quizList",quizList)).andReturn();
	
	}
	
	@Test
	void testEmptyViewQuizzes() throws Exception
	{
		List<Quiz> quizList=List.of();
		Mockito.when(quizLibraryServiceImpl.viewQuiz()).thenReturn(quizList);
		mockMvc.perform(get("/viewAllQuizzes")).andExpect(status().isOk())
		.andExpect(view().name("viewQuizzes.jsp")).andExpect(model().attributeExists("message"))
		.andExpect(model().attribute("message","There are No Quizzes to display")).andReturn();
	}
	
	@Test
	void testCreateQuiz() throws Exception
	{
		question.setId(1); 
		Quiz quiz=new Quiz("python",2);
		List<Integer> questionIds=Arrays.asList(1);
		Mockito.when(quizLibraryServiceImpl.addQuiz(any(),eq(questionIds))).thenReturn(quiz);
		mockMvc.perform(post("/createQuiz").param("questionIdList","1")).andExpect(status().isOk())
		.andExpect(view().name("quizLibraryOperationStatus.jsp")).andExpect(model().attributeExists("message"))
		.andExpect(model().attribute("message", "Quiz with id: 0 created successfully")).andReturn();
	
	}
	
	@Test
	void testCreateExistingQuiz() throws Exception
	{
		
		List<Integer> questionIds=Arrays.asList(1);
		Mockito.when(quizLibraryServiceImpl.addQuiz(any(), eq(questionIds))).thenThrow(new RedundantQuizException());
		mockMvc.perform(post("/createQuiz").param("title", "java").param("marks","2").param("questionIdList","1")).andExpect(status().isOk())
		.andExpect(view().name("quizLibraryOperationStatus.jsp")).andExpect(model().attributeExists("message"))
		.andExpect(model().attribute("message", "Quiz already exists")).andReturn();
	
	}
}
