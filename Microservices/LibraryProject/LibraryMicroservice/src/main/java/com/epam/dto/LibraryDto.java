package com.epam.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LibraryDto {
	@JsonProperty("username")
	UserDto userDto;
	@JsonProperty("books")
	List<BookDto> bookdtos;
}
