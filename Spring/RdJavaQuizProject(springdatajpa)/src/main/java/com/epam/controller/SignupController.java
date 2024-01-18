package com.epam.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.epam.customexception.UserException;
import com.epam.dto.UserDto;
import com.epam.entity.User;
import com.epam.service.SignUpService;

@Controller
public class SignupController {

	@Autowired
	private SignUpService signUpService;

	@PostMapping("signup")
	public ModelAndView login(UserDto userDto) {
		ModelAndView modelAndView = new ModelAndView();
		userDto.setUserType("user");
		try {
			User savedUser = signUpService.signUpService(userDto);
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