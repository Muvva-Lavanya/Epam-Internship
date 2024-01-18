package com.epam.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.epam.customexception.QuizNotFoundException;
import com.epam.customexception.RedundantQuizException;
import com.epam.entity.Quiz;
import com.epam.service.QuizLibraryServiceImpl;

@Controller
public class QuizLibraryController {
	@Autowired
	QuizLibraryServiceImpl quizLibraryServiceImpl;
	String message="message";
	String quizLibraryOperationStatus="quizLibraryOperationStatus.jsp";
	String format = "";
	@PostMapping("deleteQuiz") 
	public ModelAndView deleteQuiz(@RequestParam("id") int id) {
		ModelAndView modelAndView=new ModelAndView(quizLibraryOperationStatus);
		try {
			Quiz deletedQuiz = quizLibraryServiceImpl.deleteQuiz(id);
			format = String.format("Quiz with id: %d deleted successfully", deletedQuiz.getId());
			modelAndView.addObject(message, format);
		} catch (QuizNotFoundException e) {
			modelAndView.addObject(message, e.getMessage()); 
		}
		return modelAndView;
	} 

	@PostMapping("updateQuiz")
	public ModelAndView updateQuiz(int id,String title) {
		ModelAndView modelAndView=new ModelAndView(quizLibraryOperationStatus);
		try {
			Quiz updatedQuiz = quizLibraryServiceImpl.updateQuiz(id, title);
			format = String.format("Quiz title with id: %d updated successfully", updatedQuiz.getId());
			modelAndView.addObject(message,format);
		} catch (QuizNotFoundException e) {
			modelAndView.addObject(message, e.getMessage());
		}
		return modelAndView;
	}

	@GetMapping("viewAllQuizzes")
	public ModelAndView viewAllQuizes() {
		ModelAndView modelAndView=new ModelAndView("viewQuizzes.jsp");
		List<Quiz> quizList = quizLibraryServiceImpl.viewQuiz();
		if (!quizList.isEmpty()) {
			modelAndView.addObject("quizList", quizList);
		} else {
			modelAndView.addObject(message, "There are No Quizzes to display");
		}
		return modelAndView;
	}

	@PostMapping("createQuiz")
	public ModelAndView createQuiz(Quiz quiz,@RequestParam(value="questionIdList") List<Integer> questionIdList) {
		ModelAndView modelAndView=new ModelAndView(quizLibraryOperationStatus);
		try {
			Quiz savedQuiz = quizLibraryServiceImpl.addQuiz(quiz,questionIdList);
			format = String.format("Quiz with id: %d created successfully", savedQuiz.getId());
			modelAndView.addObject(message,format);
		}
		catch(RedundantQuizException  e)
		{
			modelAndView.addObject(message, e.getMessage());
		}
		return modelAndView;
	}
	
}
