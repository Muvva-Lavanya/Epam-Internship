package com.epam.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.epam.customexception.QuestionException;
import com.epam.customexception.QuizException;
import com.epam.dto.QuizDto;
import com.epam.entity.Question;
import com.epam.entity.Quiz;
import com.epam.service.QuestionLibraryServiceImpl;
import com.epam.service.QuizLibraryServiceImpl;

@Controller
public class QuizLibraryController {
	@Autowired
	QuizLibraryServiceImpl quizLibraryServiceImpl;
	@Autowired
	QuestionLibraryServiceImpl questionLibraryServiceImpl;
	String message = "message";
	String quizLibraryOperationStatus = "quizLibraryOperationStatus.jsp";
	String format = "";

	@GetMapping("deleteQuiz")
	public ModelAndView deleteQuiz(@RequestParam("id") int id) {
		ModelAndView modelAndView = new ModelAndView(quizLibraryOperationStatus);
		quizLibraryServiceImpl.deleteQuiz(id);
		format = String.format("Quiz with id: %d deleted successfully", id);
		modelAndView.addObject(message, format);
		return modelAndView;
	}

	@PostMapping("updateQuiz")
	public ModelAndView updateQuiz(QuizDto quizDto, @RequestParam(value = "questionIds") List<Integer> questionIds) {
		ModelAndView modelAndView = new ModelAndView(quizLibraryOperationStatus);
		try {
			QuizDto updatedQuiz = quizLibraryServiceImpl.updateQuiz(quizDto, questionIds);
			format = String.format("Quiz with id: %d updated successfully", updatedQuiz.getId());
			modelAndView.addObject(message, format);
		} catch (QuestionException e) {
			modelAndView.addObject(message, e.getMessage());
		}
		return modelAndView;
	}

	@GetMapping("viewAllQuizzes")
	public ModelAndView viewAllQuizes() {
		ModelAndView modelAndView = new ModelAndView("viewQuizzes.jsp");
		List<Quiz> quizList = quizLibraryServiceImpl.getAllQuizzes();
		if (!quizList.isEmpty()) {
			modelAndView.addObject("quizList", quizList);
		} else {
			modelAndView.addObject(message, "There are No Quizzes to display");
		}
		return modelAndView;
	}

	@GetMapping("viewQuiz")
	public ModelAndView viewQuiz(int id) {
		ModelAndView modelAndView = new ModelAndView("quizUpdation.jsp");
		QuizDto viewQuizById = quizLibraryServiceImpl.getQuizById(id);
		List<Question> questionsList = questionLibraryServiceImpl.getQuestions();
		modelAndView.addObject("questionsList", questionsList);
		modelAndView.addObject("quiz", viewQuizById);
		return modelAndView;
	}

	@PostMapping("createQuiz")
	public ModelAndView createQuiz(QuizDto quizDto, @RequestParam("questionIdList") List<Integer> questionIdList) {
		ModelAndView modelAndView = new ModelAndView(quizLibraryOperationStatus);
		try {
			QuizDto savedQuiz = quizLibraryServiceImpl.addQuiz(quizDto, questionIdList);
			format = String.format("Quiz with id: %d created successfully", savedQuiz.getId());
			modelAndView.addObject(message, format);
		} catch (QuizException e) {
			modelAndView.addObject(message, e.getMessage());
		}
		return modelAndView;
	}

	@GetMapping("quizQuestions")
	public ModelAndView quizQuestions(int id) {
		ModelAndView modelAndView = new ModelAndView("quizQuestions.jsp");
		List<Question> questionList = quizLibraryServiceImpl.getQuizById(id).getQuestion();
		modelAndView.addObject("questionList", questionList);
		return modelAndView;
	}

}
