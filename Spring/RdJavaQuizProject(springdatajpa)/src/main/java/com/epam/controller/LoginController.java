package com.epam.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.epam.customexception.UserException;
import com.epam.dto.UserDto;
import com.epam.service.LoginService;

@RestController
public class LoginController {

	@Autowired
	private LoginService loginService;
	String message = "message";
	@PostMapping("login")
	public ModelAndView login(UserDto userDto) {
		ModelAndView modelAndView = new ModelAndView();
		try {
			loginService.validateUser(userDto);
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

}
