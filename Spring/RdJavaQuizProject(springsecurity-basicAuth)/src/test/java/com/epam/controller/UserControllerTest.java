package com.epam.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
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

import com.epam.customexception.UserException;
import com.epam.dto.QuizDto;
import com.epam.dto.UserDto;
import com.epam.entity.Question;
import com.epam.entity.Quiz;
import com.epam.entity.User;
import com.epam.service.QuestionLibraryServiceImpl;
import com.epam.service.QuizLibraryServiceImpl;
import com.epam.service.UserServiceImpl;

@WebMvcTest(UserController.class)
class UserControllerTest {
	@MockBean
	QuestionLibraryServiceImpl questionLibraryServiceImpl;
	@MockBean
	QuizLibraryServiceImpl quizLibraryServiceImpl;
	@MockBean
	UserServiceImpl userServiceImpl;
	List<Quiz> quizList;
	List<Question> questionList;
	List<QuizDto> quizDtoList;
	@Autowired
	private MockMvc mockMvc;
	Quiz quiz;
	QuizDto quizDto;
	User user,user1;
	UserDto userDto,userDto1;
	@BeforeEach
	public void setUp() {
		Question question = new Question("extension for java", Arrays.asList(".c", ".java", ".py"), "Medium", "java",
				"8");
		questionList = new ArrayList<>();
		questionList.add(question);
		quiz = new Quiz(1, "java", 2, questionList);
		quizList = new ArrayList<>();
		quizList.add(quiz);
		quizDto = new QuizDto(1, "java", 2, questionList);
		quizDtoList = new ArrayList<>();
		quizDtoList.add(quizDto);
		user=new User("admin","Admin1","Epam@123");
		userDto=new UserDto("admin","Admin1","Epam@123");
		user1=new User("user","Admin1","Epam@123");
		userDto1=new UserDto("user","Admin1","Epam@123");
	}

	@Test
	void testAttemptQuiz() throws Exception {
		Mockito.when(quizLibraryServiceImpl.getQuizById(1)).thenReturn(quizDto);
		mockMvc.perform(get("/attemptQuiz").param("quizId", "1")).andExpect(status().isOk())
				.andExpect(view().name("attemptQuiz.jsp")).andExpect(model().attributeExists("questionsList"))
				.andExpect(model().attribute("questionsList", questionList))
				.andExpect(model().attributeExists("quizId")).andExpect(model().attribute("quizId", 1)).andReturn();

	}

	@Test
	void testShowQuiz() throws Exception {
		Mockito.when(quizLibraryServiceImpl.getAllQuizzes()).thenReturn(quizList);
		mockMvc.perform(get("/showQuizForUser")).andExpect(status().isOk())
				.andExpect(view().name("userQuizDisplay.jsp")).andExpect(model().attributeExists("quizList"))
				.andExpect(model().attribute("quizList", quizList)).andReturn();
	}

	@Test
    void testLoginSuccessForAdmin() throws Exception {
       when(userServiceImpl.validate(any())).thenReturn(user);
       mockMvc.perform(post("/login").param("userType","admin").param("username","Admin1").param("password","Epam@123")).andExpect(status().isOk())
		.andExpect(view().name("adminMenu.jsp"))
		.andExpect(model().attributeExists("message"))
		.andExpect(model().attribute("message","Welcome admin..")).andReturn();
	 
   }

	@Test
	void testLoginFailureForAdmin() throws Exception {
	   when(userServiceImpl.validate(any())).thenThrow(new UserException("Login unSuccessfull"));
	   mockMvc.perform(post("/login").param("userType","admin").param("username","Admin1").param("password","Epam@123")).andExpect(status().isOk())
		.andExpect(view().name("loginFailure.jsp"))
		.andExpect(model().attributeExists("message"))
		.andExpect(model().attribute("message","Login unSuccessfull")).andReturn();
	
	}
	
	@Test
	void testLoginSuccessForUser() throws Exception {
	   when(userServiceImpl.validate(any())).thenReturn(user1);
	   mockMvc.perform(post("/login").param("userType","user").param("username","Admin1").param("password","Epam@123")).andExpect(status().isOk())
		.andExpect(view().name("userMenu.jsp")) 
		.andExpect(model().attributeExists("message"))
		.andExpect(model().attribute("message","Welcome user..")).andReturn();
	
	}
	
	@Test
	void testUserTypeFailureForLogin() throws Exception {
	   when(userServiceImpl.validate(any())).thenThrow(new UserException("Login unSuccessfull"));
	   mockMvc.perform(post("/login").param("userType","admin").param("username","Admin1").param("password","Epam@123")).andExpect(status().isOk())
		.andExpect(view().name("loginFailure.jsp"))
		.andExpect(model().attributeExists("message"))
		.andExpect(model().attribute("message","Login unSuccessfull")).andReturn();
	
	}
	
	@Test
	void testSignUp() throws Exception {
		Mockito.when(userServiceImpl.signUp(any())).thenReturn(user);
		mockMvc.perform(post("/signup").param("userDto", "userDto")).andExpect(status().isOk())
				.andExpect(view().name("userMenu.jsp")).andExpect(model().attributeExists("message"))
				.andExpect(model().attribute("message", "Welcome Admin1")).andReturn();
	}

	@Test
	void testExistingUserSignUp() throws Exception {
		Mockito.when(userServiceImpl.signUp(any())).thenThrow(new UserException("User already exists"));
		mockMvc.perform(post("/signup").param("userDto", "userDto")).andExpect(status().isOk())
				.andExpect(view().name("signupFailure.jsp")).andExpect(model().attributeExists("error"))
				.andExpect(model().attribute("error", "User already exists")).andReturn();
	}

}
