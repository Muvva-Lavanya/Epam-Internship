package com.epam.controller;

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
import org.springframework.test.web.servlet.MockMvc;

import com.epam.customexception.QuizException;
import com.epam.entity.Question;
import com.epam.entity.Quiz;
import com.epam.service.QuestionLibraryServiceImpl;
import com.epam.service.QuizLibraryServiceImpl;
import com.epam.service.UserService;
@WebMvcTest(UserController.class)
 class UserControllerTest {
	@MockBean
	QuestionLibraryServiceImpl questionLibraryServiceImpl;
	@MockBean
	QuizLibraryServiceImpl quizLibraryServiceImpl;
	@MockBean
	UserService userService;
	List<Quiz> quizList;
	List<Question> questionList;
	@Autowired
	private MockMvc mockMvc;
	Quiz quiz;

	@BeforeEach
	public void setUp() {
		Question question = new Question("extension for java", Arrays.asList(".c", ".java", ".py"), "Medium", "java",
				"8");
		questionList = new ArrayList<>();
		questionList.add(question);
		quiz = new Quiz(1, "java", 2, questionList);
		quizList = new ArrayList<>();
		quizList.add(quiz);
	}

	@Test
	void testAttemptQuiz() throws Exception {
		Mockito.when(quizLibraryServiceImpl.getQuizById(1)).thenReturn(quiz);
		mockMvc.perform(get("/attemptQuiz").param("quizId", "1")).andExpect(status().isOk())
				.andExpect(view().name("attemptQuiz.jsp")).andExpect(model().attributeExists("questionsList"))
				.andExpect(model().attribute("questionsList", questionList))
				.andExpect(model().attributeExists("quizId")).andExpect(model().attribute("quizId", 1)).andReturn();

	}
 
	@Test
	void testShowQuiz() throws Exception {
		Mockito.when(quizLibraryServiceImpl.getAllQuizzes()).thenReturn(quizList);
		mockMvc.perform(get("/showQuizForUser")).andExpect(status().isOk()).andExpect(view().name("userQuizDisplay.jsp"))
				.andExpect(model().attributeExists("quizList"))
				.andExpect(model().attribute("quizList",quizList)).andReturn();
	}
	
	@Test
	void testCountMarks() throws Exception{
		List<String> options=Arrays.asList(".java");
		long marks=2;
		Mockito.when(userService.countMarks(1, options)).thenReturn(marks);
		mockMvc.perform(post("/countMarks").param("quizId", "1").param("options",".java")).andExpect(status().isOk())
		.andExpect(view().name("marks.jsp")).andExpect(model().attributeExists("marks"))
		.andExpect(model().attribute("marks", marks)).andReturn();
		
	}
	@Test
	void testCountMarksForUnknownId() throws Exception{
		List<String> options=Arrays.asList(".java");
		Mockito.when(userService.countMarks(100, options)).thenThrow(new QuizException("Quiz with id doesnt exist. Please try with proper id"));
		mockMvc.perform(post("/countMarks").param("quizId", "100").param("options",".java")).andExpect(status().isOk())
		.andExpect(view().name("userFailure.jsp")).andExpect(model().attributeExists("message"))
		.andExpect(model().attribute("message","Quiz with id doesnt exist. Please try with proper id")).andReturn();
		
	}
}
