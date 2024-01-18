package com.epam.restapi;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.epam.dto.UserDto;
import com.epam.entity.User;
import com.epam.service.UserServiceImpl;

import jakarta.validation.Valid;
@RestController
@RequestMapping("users")
public class UserRestController {
	@Autowired
	UserServiceImpl userServiceImpl;
	Logger logger = LogManager.getLogger(UserRestController.class);

	@PostMapping("/login")
	public ResponseEntity<User> login(@Valid @RequestBody UserDto userDto){
		logger.info("Received post request to login");
		return new ResponseEntity<>(userServiceImpl.validate(userDto),HttpStatus.OK);
	} 
	
	@PostMapping("/signup")
	public ResponseEntity<User> signup(@Valid @RequestBody UserDto userDto) {
		logger.info("Received post request to signup");
		 return new ResponseEntity<>(userServiceImpl.signUp(userDto),HttpStatus.CREATED);
		
	}
}
