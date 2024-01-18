package com.epam.entity;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name = "quiz_library")
public class Quiz {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;
	@NonNull
	String title;
	@NonNull
	Integer marks;
	@ManyToMany(targetEntity = Question.class)
	List<Question> question;

	
}
