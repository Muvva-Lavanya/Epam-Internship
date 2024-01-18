package com.epam.service;

import java.util.List;

import com.epam.dto.request.UserRequestDto;
import com.epam.dto.response.UserResponseDto;

public interface UserService {
	UserResponseDto addUser(UserRequestDto userRequestDto);
	UserResponseDto updateUser(UserRequestDto userRequestDto,String username);
	void deleteUser(String username);
	UserResponseDto getUserByUsername(String username);
	List<UserResponseDto> getAllUsers();

}
