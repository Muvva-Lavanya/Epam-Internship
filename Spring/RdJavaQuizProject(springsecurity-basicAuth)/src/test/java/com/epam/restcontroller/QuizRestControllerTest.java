package com.epam.restcontroller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
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
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.epam.dto.QuizDto;
import com.epam.entity.Question;
import com.epam.entity.Quiz;
import com.epam.service.QuestionLibraryServiceImpl;
import com.epam.service.QuizLibraryServiceImpl;
import com.epam.service.UserServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
@WebMvcTest(controllers=QuizRestControllerTest.class,excludeAutoConfiguration= {SecurityAutoConfiguration.class})
class QuizRestControllerTest {
	@MockBean
	QuestionLibraryServiceImpl questionLibraryServiceImpl;
	@MockBean
	QuizLibraryServiceImpl quizLibraryServiceImpl;
	@MockBean
	UserServiceImpl userServiceImpl;
	@Autowired
	MockMvc mockMvc;
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
	void testCreateQuiz() throws Exception {
		Mockito.when(quizLibraryServiceImpl.addQuiz(any(),eq(List.of(1)))).thenReturn(quizDto);
		mockMvc.perform(post("/quizzes")
			   .param("questionIds", "1")
               .contentType(MediaType.APPLICATION_JSON)
               .content(new ObjectMapper().writeValueAsString(quizDto)))
               .andExpect(status().isCreated())
               .andExpect(jsonPath("$.title").value("java"));
              
	}

	@Test
	void testDeleteQuiz() throws Exception {

		Mockito.doNothing().when(quizLibraryServiceImpl).deleteQuiz(1);
		mockMvc.perform(delete("/quizzes/{id}", 1)).andExpect(status().isNoContent()).andReturn();
	}

	@Test
	void testViewQuizById() throws Exception {
		Mockito.when(quizLibraryServiceImpl.getQuizById(1)).thenReturn(quizDto);
		mockMvc.perform(get("/quizzes/{id}", 1)).andExpect(status().isOk())
				.andExpect(jsonPath("$.title").value("java")).andReturn();
	}
	

	@Test
	void testViewAllQuizzes() throws Exception {
		Mockito.when(quizLibraryServiceImpl.getAllQuizzes()).thenReturn(List.of(quiz));
		mockMvc.perform(get("/quizzes")).andExpect(status().isOk())
				.andExpect(jsonPath("$[0].title").value("java")).andReturn();
	}
	
	@Test
	void testUpdateQuiz() throws Exception
	{
		
		Mockito.when(quizLibraryServiceImpl.updateQuiz(any(),eq(List.of(1)))).thenReturn(quizDto);
		mockMvc.perform(put("/quizzes")
			   .param("questionIds", "1")
               .contentType(MediaType.APPLICATION_JSON)
               .content(new ObjectMapper().writeValueAsString(quizDto)))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.title").value("java"))
               .andReturn();
	}

}
