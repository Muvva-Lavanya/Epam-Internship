package com.epam.restapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.epam.dto.UserDto;
import com.epam.entity.User;
import com.epam.service.UserServiceImpl;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
@RestController
@RequestMapping("users")
@Slf4j
public class UserRestController {
	@Autowired
	UserServiceImpl userServiceImpl;

	@PostMapping("/login")
	public ResponseEntity<User> login(@Valid @RequestBody UserDto userDto){
		log.info("Received post request to login");
		return new ResponseEntity<>(userServiceImpl.validate(userDto),HttpStatus.OK);
	} 
	
	@PostMapping("/signup")
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<User> signup(@Valid @RequestBody UserDto userDto) {
		log.info("Received post request to signup");
		 return new ResponseEntity<>(userServiceImpl.signUp(userDto),HttpStatus.CREATED);
		
	}
}
