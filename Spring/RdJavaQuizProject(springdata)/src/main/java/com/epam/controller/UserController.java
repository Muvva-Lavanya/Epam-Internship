//package com.epam.controller;
//
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.servlet.ModelAndView;
//
//import com.epam.entity.Question;
//import com.epam.entity.Quiz;
//import com.epam.service.QuizLibraryServiceImpl;
//import com.epam.service.UserService;
//
//@Controller
//public class UserController {
//	@Autowired
//	QuizLibraryServiceImpl quizLibraryServiceImpl;
//	@Autowired
//	UserService userService;
//
//	@GetMapping("showQuizForUser")
//	public ModelAndView showQuiz() {
//		ModelAndView modelAndView = new ModelAndView();
//		List<Quiz> quizList = quizLibraryServiceImpl.viewQuiz();
//		modelAndView.addObject("quizList", quizList);
//		modelAndView.setViewName("userQuizDisplay.jsp");
//		return modelAndView;
//	}
//
//	@GetMapping("attemptQuiz")
//	public ModelAndView showAttemptQuiz(@RequestParam("quizId") int quizId) {
//		ModelAndView modelAndView = new ModelAndView();
//		List<Question> questionList = quizLibraryServiceImpl.viewQuizById(quizId).getQuestion();
//		modelAndView.addObject("questionsList", questionList);
//		modelAndView.addObject("quizId", quizId);
//		modelAndView.setViewName("attemptQuiz.jsp");
//		return modelAndView;
//	}
//
//	@PostMapping("countMarks")
//	public ModelAndView countMarks(@RequestParam("quizId") int quizId, @RequestParam("options") List<String> options) {
//		ModelAndView modelAndView = new ModelAndView();
//		modelAndView.addObject("marks", userService.countMarks(quizId, options));
//		modelAndView.setViewName("marks.jsp");
//		return modelAndView;
//	}
//}
