package com.epam.restcontroller;

import java.util.List;

import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.epam.dto.request.UserRequestDto;
import com.epam.dto.response.UserResponseDto;

import jakarta.validation.Valid;

@FeignClient(name = "users",fallback = UserFeignClientImpl.class)
@LoadBalancerClient(name="users", configuration = UserFeignClientImpl.class)
public interface UserFeignClient {
	
	@GetMapping("users")
	public ResponseEntity<List<UserResponseDto>> getAllUsers();
	
	@GetMapping("users/{username}")
	public ResponseEntity<UserResponseDto> getUser(@PathVariable String username);
	
	@PostMapping("users")
	public ResponseEntity<UserResponseDto> addUser(@RequestBody @Valid UserRequestDto userDto);
	
	@PutMapping("users/{username}")
	public ResponseEntity<UserResponseDto> updateUser(@PathVariable String username, @RequestBody @Valid UserRequestDto userDto);
	
	@DeleteMapping("users/{username}")
	public void deleteBookByUsername(@PathVariable String username);
		

}