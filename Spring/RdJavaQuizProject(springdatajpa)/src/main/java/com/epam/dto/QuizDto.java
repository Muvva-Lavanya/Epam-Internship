package com.epam.dto;

import java.util.List;

import com.epam.entity.Question;

public class QuizDto {
	int id;
	String title;
	int marks;
	List<Question> question;

	public QuizDto(String title, int marks) {
		super();
		this.title = title;
		this.marks = marks;
	}

	public QuizDto(int id, String title, int marks, List<Question> question) {
		super();
		this.id = id;
		this.title = title;
		this.marks = marks;
		this.question = question;
	}

	public QuizDto() {
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
		return "QuizDto" + id + ", title=" + title + ", marks=" + marks + ", question=" + question;
	}

	
}
