package com.epam.dto;

import java.util.Date;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.AccessMode;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@NoArgsConstructor
public class BatchDto {
	@Schema(accessMode = AccessMode.READ_ONLY)
	private int id;
	@NotBlank(message = "Enter valid name")
	@Size(min=3,max=15,message="Name should contain only 3 to 15 characters")
	private String name;
	@NotBlank(message = "Enter valid practice domain")
	private String practice;
	@NotBlank(message = "Enter valid Start Date")
	private Date startDate;
	@NotBlank(message = "Enter valid End Date")
	private Date endDate;
	

}
