package com.epam.dto.response;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Builder;
import lombok.Data;
@Data
@Builder
@JsonInclude(value = Include.NON_NULL)
public class UserResponseDto {
	private String username;
	private String name;
	private String email;
	private String timestamp;
	private String developerMessage;
	private HttpStatus httpStatus;
}
