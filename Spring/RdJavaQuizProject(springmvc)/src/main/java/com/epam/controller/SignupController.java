package com.epam.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.epam.customexception.RedundantUserException;
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
	        User user=new User(userDto);
	        try { 
	        	User savedUser=signUpService.signUpService(user);
				String format=String.format("Welcome %s",savedUser.getUsername());
				modelAndView.addObject("message",format);
				modelAndView.setViewName("userMenu.jsp");
			} catch (RedundantUserException e ) {
				modelAndView.addObject("error","User already exists");
				modelAndView.setViewName("signupFailure.jsp");
			}
	        return modelAndView; 
	    }

}