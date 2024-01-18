package com.epam.dto.response;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Builder;
import lombok.Data;
@Data
@Builder
@JsonInclude(value = Include.NON_NULL)
public class BookResponseDto {
	private String name;
	private String author;
	private String publisher;
	private String timestamp;
	private String developerMessage;
	private HttpStatus httpStatus;
}
