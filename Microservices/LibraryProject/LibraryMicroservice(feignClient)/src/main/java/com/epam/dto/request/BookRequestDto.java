package com.epam.dto.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;
@Data
@Builder
@JsonInclude(value = Include.NON_NULL)
public class BookRequestDto {
	@NotBlank(message = "Enter proper Book name")
	private String name;
	@NotBlank(message = "Enter proper Author name")
	private String author;
	@NotBlank(message = "Enter proper Publisher name")
	private String publisher;
}
