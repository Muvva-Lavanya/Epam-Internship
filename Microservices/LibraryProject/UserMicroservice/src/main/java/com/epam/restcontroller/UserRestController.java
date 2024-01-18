package com.epam.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.epam.dto.request.UserRequestDto;
import com.epam.dto.response.UserResponseDto;
import com.epam.service.UserServiceImpl;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("users")
public class UserRestController {
	@Autowired
	UserServiceImpl userServiceImpl;

	@GetMapping
	public ResponseEntity<List<UserResponseDto>> getAllUsers() {
		log.info("Received get request to view all users");
		return new ResponseEntity<>(userServiceImpl.getAllUsers(), HttpStatus.OK);
	}
	
	@GetMapping("/{username}")
	public ResponseEntity<UserResponseDto> getUserByUsername(@PathVariable String username) {
		log.info("Received get request to view user by username");
		return new ResponseEntity<>(userServiceImpl.getUserByUsername(username),HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<UserResponseDto> createQuestion(@Valid @RequestBody UserRequestDto userRequestDto) {
		log.info("Received post request to create user {} ",userRequestDto);
		return new ResponseEntity<>(userServiceImpl.addUser(userRequestDto),HttpStatus.CREATED);
	}
	
	@DeleteMapping("/{username}")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void delete(@PathVariable String username){
		log.info("Received delete request to delete user");
		userServiceImpl.deleteUser(username);
	}
	
	@PutMapping("/{username}")
	public ResponseEntity<UserResponseDto> update(@PathVariable String username,@Valid @RequestBody UserRequestDto userRequestDto) {
		log.info("Received put request to update User");
		return new ResponseEntity<>(userServiceImpl.updateUser(userRequestDto,username),HttpStatus.OK);
	}

}
