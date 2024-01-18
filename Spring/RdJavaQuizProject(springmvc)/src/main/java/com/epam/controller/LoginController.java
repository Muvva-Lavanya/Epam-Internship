package com.epam.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import com.epam.service.LoginService;

@Controller
public class LoginController {

	@Autowired
	private LoginService loginService;
	String message="message";
	@PostMapping("login")
	public ModelAndView login(String userType,String username,String password) {
		ModelAndView modelAndView = new ModelAndView();
		if (loginService.validateUser(userType,username,password) && userType.equals("admin")) {
				modelAndView.addObject(message, "Welcome admin....");
				modelAndView.setViewName("adminMenu.jsp");
			}  
			else if(loginService.validateUser(userType,username,password) && userType.equals("user")) {
				modelAndView.addObject(message, "Welcome user");
				modelAndView.setViewName("userMenu.jsp");
			}
		else {
			modelAndView.addObject(message, "Login Unsuccessfull");
			modelAndView.setViewName("loginFailure.jsp");
		}
		return modelAndView;  
	}
	
	
}
