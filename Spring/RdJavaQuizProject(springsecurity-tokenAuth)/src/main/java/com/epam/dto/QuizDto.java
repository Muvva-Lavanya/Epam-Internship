package com.epam.dto;

import java.util.List;

import com.epam.entity.Question;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
@Data
@AllArgsConstructor
@RequiredArgsConstructor
@NoArgsConstructor
public class QuizDto {
	int id;
	@Size(min=1,max=10,message="Title should be 1 to 10 characters")
	@NonNull
	String title;
	@Min(value = 1,message="question weightage should not be negative and greater than zero")
	@NonNull
	Integer marks;
	List<Question> question;

	
}
