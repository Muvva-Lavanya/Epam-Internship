package com.epam.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.epam.customexception.QuestionException;
import com.epam.dto.QuestionDto;
import com.epam.entity.Question;
import com.epam.service.QuestionLibraryServiceImpl;

@Controller
public class QuestionLibraryController {
	@Autowired
	QuestionLibraryServiceImpl questionLibraryServiceImpl;
	String format = "";
	String message = "message";
	String questionLibraryOperationStatus = "questionLibraryOperationStatus.jsp";

	@GetMapping("deleteQuestion")
	public ModelAndView deleteQuestion(@RequestParam("id") int id) {
		ModelAndView modelAndView = new ModelAndView(questionLibraryOperationStatus);
			questionLibraryServiceImpl.deleteQuestion(id);
			format = String.format("Question with id: %d deleted successfully", id);
			modelAndView.addObject(message, format);
		return modelAndView;
	}

	@GetMapping("viewquestions")
	public ModelAndView showQuestions() {
		ModelAndView modelAndView = new ModelAndView("viewQuestions.jsp");
		List<Question> questionsList = questionLibraryServiceImpl.getQuestions();
		if (!questionsList.isEmpty()) {
			modelAndView.addObject("questionsList", questionsList);
		} else {
			modelAndView.addObject(message, "There are No Questions to display");
		}
		return modelAndView;
	}

	@PostMapping("createQuestion")
	public ModelAndView createQuestion(QuestionDto questionDto) {
		ModelAndView modelAndView = new ModelAndView(questionLibraryOperationStatus);
		try {
			QuestionDto savedQuestion = questionLibraryServiceImpl.createQuestion(questionDto);
			format = String.format("Question with id: %d created successfully", savedQuestion.getId());
			modelAndView.addObject(message, format);
		} catch (QuestionException e) {
			modelAndView.addObject(message, e.getMessage());
		}
		return modelAndView;
	}

	@GetMapping("viewQuestion")
	public ModelAndView modelAndView(int id) {
		ModelAndView modelAndView = new ModelAndView("questionUpdation.jsp");
		try {
			QuestionDto viewQuestionById = questionLibraryServiceImpl.getQuestionById(id);
			String options = String.join(",", viewQuestionById.getOptions());
			modelAndView.addObject("options", options);
			modelAndView.addObject("question", viewQuestionById);
		} catch (QuestionException e) {
			modelAndView.addObject(message, e.getMessage());
		}
		return modelAndView;
	}

	@PostMapping("updateQuestion")
	public ModelAndView modelAndView(QuestionDto questionDto) {
		ModelAndView modelAndView = new ModelAndView(questionLibraryOperationStatus);
		QuestionDto updatedQuestion = questionLibraryServiceImpl.updateQuestion(questionDto);
		format = String.format("Question with id :%d updated successfully", updatedQuestion.getId());
		modelAndView.addObject(message, format);
		return modelAndView;

	}

	@GetMapping("questionsList")
	public ModelAndView questionsList() {
		ModelAndView modelAndView = new ModelAndView("quizCreation.jsp");
		List<Question> questionList = questionLibraryServiceImpl.getQuestions();
		modelAndView.addObject("questionList", questionList);
		return modelAndView;
	}

}
