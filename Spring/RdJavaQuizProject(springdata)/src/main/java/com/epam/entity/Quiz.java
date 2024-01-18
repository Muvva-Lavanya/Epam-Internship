package com.epam.entity;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
@Entity
@Table(name="quiz_library")
public class Quiz {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	int id;
	@Column(unique = true)
	String title;
	int marks;
	@ManyToMany(targetEntity = Question.class)
	List<Question> question;
	public Quiz(String title,int marks) { 
		super();
		this.title = title;
		this.marks = marks;
	}
	public Quiz(int id, String title,int marks, List<Question> question) {
		super();
		this.id = id;
		this.title = title;
		this.marks = marks;
		this.question = question;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	public int getMarks() {
		return marks;
	}
	public Quiz() {
	}
	public void setMarks(int marks) {
		this.marks = marks;
	}
	public List<Question> getQuestion() {
		return question;
	}
	public void setQuestion(List<Question> question) {
		this.question = question;
	}
	@Override
	public String toString() {
		return "Quiz " + id + ". " + title + "- " + "Questions - "
				+ marks +"Marks";
	}

	

}
