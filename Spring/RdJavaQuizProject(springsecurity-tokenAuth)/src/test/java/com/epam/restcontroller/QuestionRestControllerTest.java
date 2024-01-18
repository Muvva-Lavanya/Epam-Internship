package com.epam.restcontroller;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import com.epam.customexception.QuestionException;
import com.epam.dto.QuestionDto;
import com.epam.entity.Question;
import com.epam.restapi.QuestionRestController;
import com.epam.service.QuestionLibraryServiceImpl;
import com.epam.service.QuizLibraryServiceImpl;
import com.epam.service.UserServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
@WebMvcTest(controllers=QuestionRestController.class)
class QuestionRestControllerTest {
	@MockBean
	QuestionLibraryServiceImpl questionLibraryServiceImpl;
	@MockBean
	QuizLibraryServiceImpl quizLibraryServiceImpl;
	@MockBean
	UserServiceImpl userServiceImpl;
	@Autowired
	MockMvc mockMvc;
	Question question;
	QuestionDto questionDto;
 
	@BeforeEach
	public void setUp() {
		questionDto = new QuestionDto(1,"extension for java", Arrays.asList(".c", ".java", ".py"), "Medium", "java",
				"8");
		question = new Question("extension for java", Arrays.asList(".c", ".java", ".py"), "Medium", "java", "8");
		question.setId(1);
	}

	@Test
	@WithMockUser(value = "spring")
	void testCreateQuestion() throws Exception
	{
		Mockito.when(questionLibraryServiceImpl.createQuestion(any())).thenReturn(questionDto);
		mockMvc.perform(post("/questions")
		 .contentType(MediaType.APPLICATION_JSON)
         .content(new ObjectMapper().writeValueAsString(questionDto)).with(csrf()))
		.andExpect(jsonPath("$.title").value("extension for java"))
			.andExpect(status().isCreated())
			.andReturn();
	} 
	
	@Test
	@WithMockUser(value = "spring")
	void testCreateQuestionWithException() throws Exception
	{
		Mockito.when(questionLibraryServiceImpl.createQuestion(any())).thenThrow(QuestionException.class);
		mockMvc.perform(post("/questions")
		 .contentType(MediaType.APPLICATION_JSON)
         .content(new ObjectMapper().writeValueAsString(questionDto)).with(csrf()))
			.andExpect(status().isOk())
			.andReturn();
	} 
 
	@Test
	@WithMockUser(value = "spring")
	void testDeleteQuestion() throws Exception {

		Mockito.doNothing().when(questionLibraryServiceImpl).deleteQuestion(1);
		mockMvc.perform(delete("/questions/{id}", 1).with(csrf())).andExpect(status().isNoContent()).andReturn();
	}

	@Test
	@WithMockUser(value = "spring")
	void testViewQuestionById() throws Exception {
		Mockito.when(questionLibraryServiceImpl.getQuestionById(1)).thenReturn(questionDto);
		mockMvc.perform(get("/questions/{id}", 1)).andExpect(status().isOk())
				.andExpect(jsonPath("$.title").value("extension for java")).andReturn();
	}
	
	@Test
	@WithMockUser(value = "spring")
	void testViewQuestionByIdException() throws Exception {
		Mockito.when(questionLibraryServiceImpl.getQuestionById(1)).thenThrow(QuestionException.class);
		mockMvc.perform(get("/questions/{id}", 1)).andExpect(status().isOk()).andReturn();
	}

	@Test
	@WithMockUser(value = "spring")
	void testViewAllQuestions() throws Exception {
		Mockito.when(questionLibraryServiceImpl.getQuestions()).thenReturn(List.of(question));
		mockMvc.perform(get("/questions")).andExpect(status().isOk())
				.andExpect(jsonPath("$[0].title").value("extension for java"));
	}
	
	@Test
	@WithMockUser(value = "spring")
	void testUpdateQuestion() throws Exception
	{
		Mockito.when(questionLibraryServiceImpl.updateQuestion(any())).thenReturn(questionDto);
		mockMvc.perform(put("/questions")
		 .contentType(MediaType.APPLICATION_JSON)
         .content(new ObjectMapper().writeValueAsString(questionDto)).with(csrf()))
		.andExpect(jsonPath("$.title").value("extension for java"))
			.andExpect(status().isOk())
			.andReturn();
	}
	
	@Test
	@WithMockUser(value = "spring")
	void testUpdateQuestionWithException() throws Exception
	{
		Mockito.when(questionLibraryServiceImpl.updateQuestion(any())).thenThrow(QuestionException.class);
		mockMvc.perform(put("/questions")
		 .contentType(MediaType.APPLICATION_JSON)
         .content(new ObjectMapper().writeValueAsString(questionDto)).with(csrf()))
			.andExpect(status().isOk())
			.andReturn();
	} 

}
