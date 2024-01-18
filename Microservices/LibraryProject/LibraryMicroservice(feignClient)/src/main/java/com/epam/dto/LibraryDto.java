package com.epam.dto;

import java.util.List;

import com.epam.dto.response.BookResponseDto;
import com.epam.dto.response.UserResponseDto;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LibraryDto {
	@JsonProperty("username")
	UserResponseDto userDto;
	@JsonInclude(value = Include.NON_EMPTY)
	@JsonProperty("books")
	List<BookResponseDto> bookdtos;
}
