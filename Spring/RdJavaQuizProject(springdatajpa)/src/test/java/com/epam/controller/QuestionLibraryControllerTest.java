package com.epam.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.web.servlet.MockMvc;

import com.epam.customexception.QuestionException;
import com.epam.dto.QuestionDto;
import com.epam.entity.Question;
import com.epam.service.QuestionLibraryServiceImpl;
@WebMvcTest(QuestionLibraryController.class)
class QuestionLibraryControllerTest {
	@MockBean
	private QuestionLibraryServiceImpl questionLibraryServiceImpl;
	@Autowired
	private MockMvc mockMvc;
	Question question;
	QuestionDto questionDto;
	List<Question> questionList; 

	@BeforeEach
	public void setUp() {
		questionDto = new QuestionDto(1,"extension for java", Arrays.asList(".c", ".java", ".py"), "Medium", "java", "8");
		question = new Question("extension for java", Arrays.asList(".c", ".java", ".py"), "Medium", "java", "8");
	}

	@Test
	void testDeleteExistingQuestion() throws Exception {
		question.setId(1);
		Mockito.when(questionLibraryServiceImpl.deleteQuestion(1)).thenReturn(questionDto);
		mockMvc.perform(get("/deleteQuestion").param("id", "1")).andExpect(status().isOk())
				.andExpect(view().name("questionLibraryOperationStatus.jsp"))
				.andExpect(model().attributeExists("message"))
				.andExpect(model().attribute("message", "Question with id: 1 deleted successfully")).andReturn();
	}

	@Test
	void testDeleteUnExistingQuestion() throws Exception {
		question.setId(100);
		Mockito.when(questionLibraryServiceImpl.deleteQuestion(1000)).thenThrow(new QuestionException("Question id doesnt exists"));
		mockMvc.perform(get("/deleteQuestion").param("id", "1000")).andExpect(status().isOk())
				.andExpect(view().name("questionLibraryOperationStatus.jsp"))
				.andExpect(model().attributeExists("message"))
				.andExpect(model().attribute("message", "Question id doesnt exists")).andReturn();
	}
	
	@Test
	void testDeleteForeignKeyQuestion() throws Exception {
		question.setId(100);
		Mockito.when(questionLibraryServiceImpl.deleteQuestion(100)).thenThrow(new DataIntegrityViolationException("Question cannot be deleted as violating foreign key constraints"));
		mockMvc.perform(get("/deleteQuestion").param("id", "100")).andExpect(status().isOk())
				.andExpect(view().name("questionLibraryOperationStatus.jsp"))
				.andExpect(model().attributeExists("message"))
				.andExpect(model().attribute("message", "Question cannot be deleted as violating foreign key constraints")).andReturn();
	}

	
	@Test
	void testViewQuestions() throws Exception
	{
		questionList=new ArrayList<>();
		questionList.add(question);
		Mockito.when(questionLibraryServiceImpl.getQuestions()).thenReturn(questionList);
		mockMvc.perform(get("/viewquestions")).andExpect(status().isOk())
				.andExpect(view().name("viewQuestions.jsp")).andExpect(model().attributeExists("questionsList"))
				.andExpect(model().attribute("questionsList",questionList)).andReturn();
	}
	
	@Test
	void testViewEmptyQuestions() throws Exception
	{
		questionList=List.of();
		Mockito.when(questionLibraryServiceImpl.getQuestions()).thenReturn(questionList);
		mockMvc.perform(get("/viewquestions")).andExpect(status().isOk())
				.andExpect(view().name("viewQuestions.jsp")).andExpect(model().attributeExists("message"))
				.andExpect(model().attribute("message","There are No Questions to display")).andReturn();
	}
	
	@Test
	void testCreateQuestion() throws Exception
	{
		question.setId(1);
		Mockito.when(questionLibraryServiceImpl.createQuestion(any())).thenReturn(questionDto);
		mockMvc.perform(post("/createQuestion").param("questionDto","questionDto")).andExpect(status().isOk())
				.andExpect(view().name("questionLibraryOperationStatus.jsp"))
				.andExpect(model().attributeExists("message"))
				.andExpect(model().attribute("message","Question with id: 1 created successfully")).andReturn();
	}
	
	@Test
	void testCreateExistingQuestion() throws Exception
	{
		Mockito.when(questionLibraryServiceImpl.createQuestion(any())).thenThrow(new QuestionException("Question already exists"));
		mockMvc.perform(post("/createQuestion").param("questionDto","questionDto")).andExpect(status().isOk())
				.andExpect(view().name("questionLibraryOperationStatus.jsp"))
				.andExpect(model().attributeExists("message"))
				.andExpect(model().attribute("message","Question already exists")).andReturn();
	
	}
	
	@Test
	void testViewQuestionById() throws Exception
	{
		question.setId(1);
		Mockito.when(questionLibraryServiceImpl.getQuestionById(1)).thenReturn(questionDto);
		mockMvc.perform(get("/viewQuestion").param("id","1")).andExpect(status().isOk())
				.andExpect(view().name("questionUpdation.jsp")).andExpect(model().attributeExists("question"))
				.andExpect(model().attribute("question",questionDto)).andReturn();
	}
	
	@Test
	void testViewQuestionByUnKnownId() throws Exception
	{
		question.setId(1);
		Mockito.when(questionLibraryServiceImpl.getQuestionById(100)).thenThrow(new QuestionException("Question id doesnt exist.Please try with different id"));
		mockMvc.perform(get("/viewQuestion").param("id","100")).andExpect(status().isOk())
				.andExpect(view().name("questionUpdation.jsp")).andExpect(model().attributeExists("message"))
				.andExpect(model().attribute("message","Question id doesnt exist.Please try with different id")).andReturn();
	}
	@Test
	void testUpdateQuestion() throws Exception
	{
		Mockito.when(questionLibraryServiceImpl.updateQuestion(any())).thenReturn(questionDto);
		mockMvc.perform(post("/updateQuestion").param("id","0").param("questionDto","questionDto")).andExpect(status().isOk())
		.andExpect(view().name("questionLibraryOperationStatus.jsp"))
		.andExpect(model().attributeExists("message"))
		.andExpect(model().attribute("message","Question with id :1 updated successfully")).andReturn();
	}
	
	@Test
	void testQuizQuestions() throws Exception
	{
		questionList=new ArrayList<>();
		questionList.add(question);
		Mockito.when(questionLibraryServiceImpl.getQuestions()).thenReturn(questionList);
		mockMvc.perform(get("/questionsList")).andExpect(status().isOk())
				.andExpect(view().name("quizCreation.jsp")).andExpect(model().attributeExists("questionList"))
				.andExpect(model().attribute("questionList",questionList)).andReturn();
	}
	

}
