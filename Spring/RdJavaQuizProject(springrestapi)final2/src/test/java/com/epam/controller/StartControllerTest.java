package com.epam.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.epam.service.QuestionLibraryServiceImpl;
import com.epam.service.QuizLibraryServiceImpl;
import com.epam.service.UserServiceImpl;
@WebMvcTest(TestController.class)
 class StartControllerTest {
	@MockBean
	QuestionLibraryServiceImpl questionLibraryServiceImpl;
	@MockBean
	QuizLibraryServiceImpl quizLibraryServiceImpl;
	@MockBean
	UserServiceImpl userServiceImpl;
	@Autowired
	MockMvc mockMvc;
	@Test
	void testStart() throws Exception
	{
		 mockMvc.perform(get("/")).andExpect(status().isOk())
			.andExpect(view().name("homePage.jsp")).andReturn();
	}
	
}
