package com.epam.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.epam.customexception.UserException;
import com.epam.dto.UserDto;
import com.epam.entity.Question;
import com.epam.entity.Quiz;
import com.epam.entity.User;
import com.epam.service.QuizLibraryServiceImpl;
import com.epam.service.UserServiceImpl;

@Controller
public class UserController {
	@Autowired
	QuizLibraryServiceImpl quizLibraryServiceImpl;
	@Autowired
	UserServiceImpl userServiceImpl;
	String message = "message";
	@GetMapping("showQuizForUser")
	public ModelAndView showQuiz() {
		ModelAndView modelAndView = new ModelAndView();
		List<Quiz> quizList = quizLibraryServiceImpl.getAllQuizzes();
		modelAndView.addObject("quizList", quizList);
		modelAndView.setViewName("userQuizDisplay.jsp");
		return modelAndView;
	}

	@GetMapping("attemptQuiz")
	public ModelAndView showAttemptQuiz(@RequestParam("quizId") int quizId) {
		ModelAndView modelAndView = new ModelAndView();
		List<Question> questionList = quizLibraryServiceImpl.getQuizById(quizId).getQuestion();
		modelAndView.addObject("questionsList", questionList);
		modelAndView.addObject("quizId", quizId);
		modelAndView.setViewName("attemptQuiz.jsp");
		return modelAndView;
	}

	@PostMapping("login")
	public ModelAndView login(UserDto userDto) {
		ModelAndView modelAndView = new ModelAndView();
		try {
			userServiceImpl.validate(userDto);
			modelAndView.addObject(message,"Welcome "+ userDto.getUserType()+".".repeat(2));
			modelAndView.setViewName(userDto.getUserType()+"Menu.jsp"); 
		}
		catch(UserException e)
		{
			modelAndView.addObject(message,e.getMessage());
			modelAndView.setViewName("loginFailure.jsp"); 
		}
		return modelAndView;
	}
	
	@PostMapping("signup")
	public ModelAndView signUp(UserDto userDto) {
		ModelAndView modelAndView = new ModelAndView();
		userDto.setUserType("user");
		try {
			User savedUser = userServiceImpl.signUp(userDto);
			String format = String.format("Welcome %s", savedUser.getUsername());
			modelAndView.addObject("message", format);
			modelAndView.setViewName("userMenu.jsp");
		} catch (UserException e) {
			modelAndView.addObject("error", "User already exists");
			modelAndView.setViewName("signupFailure.jsp");
		}
		return modelAndView;
	}
}
