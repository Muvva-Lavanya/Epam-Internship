package com.epam.dto;

import com.epam.model.Batch;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.AccessMode;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class AssociateDto {
	@Schema(accessMode = AccessMode.READ_ONLY)
	private int id;
	@NotBlank(message = "Enter valid name")
	private String name;
	@NotBlank(message = "Enter valid email")
	@Email(message= "Email must be in the form of abc@gmail.com")
	private String email;
	@NotBlank(message="Enter valid gender")
	@Pattern(regexp = "^(?)(Male|Female)$",message = "Gender should be either male or Female" )
	private String gender;
	@NotBlank(message="Enter valid college name")
	private String college;
	@NotBlank(message="Enter valid status")
	@Pattern(regexp ="^(?)(Active|InActive)$",message="Status should be either active or inactive")
	private String status;
	@Valid
	private Batch batch;

}
