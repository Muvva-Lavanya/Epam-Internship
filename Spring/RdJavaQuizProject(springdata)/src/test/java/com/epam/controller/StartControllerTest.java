package com.epam.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
@ExtendWith(MockitoExtension.class)
 class StartControllerTest {
	@InjectMocks
	private TestController testController;
	MockMvc mockMvc;
	@BeforeEach
	public void setUp()
	{
		mockMvc = MockMvcBuilders.standaloneSetup(testController).build();
	}
	@Test
	void testStart() throws Exception
	{
		 mockMvc.perform(get("/")).andExpect(status().isOk())
			.andExpect(view().name("homePage.jsp")).andReturn();
	}
	
}
