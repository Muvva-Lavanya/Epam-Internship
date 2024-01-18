package com.epam.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(value = Include.NON_NULL)
public class BookDto {
	@NotBlank(message = "Enter proper Book name")
	private String name;
	@NotBlank(message = "Enter proper Author name")
	private String author;
	@NotBlank(message = "Enter proper Publisher name")
	private String publisher;
	@Min(value = 1,message = "There should be atleast one book")
	private Integer quantity;
}
