package com.epam.restcontroller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.epam.dto.request.UserRequestDto;
import com.epam.dto.response.UserResponseDto;

import jakarta.validation.Valid;
@Service
public class UserFeignClientImpl implements UserFeignClient {
	UserResponseDto userResponseDto;
	@Override
	public ResponseEntity<List<UserResponseDto>> getAllUsers() {
		userResponseDto=UserResponseDto.builder().email("email").username("username").name("name").build();
		return ResponseEntity.ok(List.of(userResponseDto));
	}

	@Override
	public ResponseEntity<UserResponseDto> getUser(String username) {
		userResponseDto=UserResponseDto.builder().email("Email").username("Username").name("Name").build();
		return ResponseEntity.ok(userResponseDto);
	}

	@Override
	public ResponseEntity<UserResponseDto> addUser(@Valid UserRequestDto userDto) {
		userResponseDto=UserResponseDto.builder().email("email").username("username").name("name").build();
		return ResponseEntity.ok(userResponseDto);
	}

	@Override
	public ResponseEntity<UserResponseDto> updateUser(String username, @Valid UserRequestDto userDto) {
		userResponseDto=UserResponseDto.builder().email("Email").username("Username").name("Name").build();
		return ResponseEntity.ok(userResponseDto);
	}

	@Override
	public void deleteBookByUsername(String username) {
		// delete by username
	}

}
