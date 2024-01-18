package com.epam.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.epam.customexception.QuestionNotFoundException;
import com.epam.customexception.RedundantQuestionException;
import com.epam.dto.QuestionDto;
import com.epam.entity.Question;
import com.epam.service1.QuestionLibraryServiceImpl;

import jakarta.persistence.RollbackException;

@Controller
public class QuestionLibraryController {
	@Autowired
	QuestionLibraryServiceImpl questionLibraryServiceImpl;
	String format = "";
	String message = "message";
	String questionLibraryOperationStatus = "questionLibraryOperationStatus.jsp";
	String updationStatus = "updationStatus.jsp";

	@GetMapping("deleteQuestion")
	public ModelAndView deleteQuestion(@RequestParam("id") int id) {
		ModelAndView modelAndView = new ModelAndView(questionLibraryOperationStatus);
		try {
			Question deletedQuestion = questionLibraryServiceImpl.deleteQuestion(id);
			format = String.format("Question with id: %d deleted successfully", deletedQuestion.getId());
			modelAndView.addObject(message, format);
		} catch (QuestionNotFoundException | RollbackException e) {
			modelAndView.addObject(message, e.getMessage());
		}
		return modelAndView;
	}

	@GetMapping(value = "viewquestions")
	public ModelAndView showQuestions() {
		ModelAndView modelAndView = new ModelAndView("viewQuestions.jsp");
		List<Question> questionsList = questionLibraryServiceImpl.viewQuestion();
		if (!questionsList.isEmpty()) {
			modelAndView.addObject("questionsList", questionsList);
		} else {
			modelAndView.addObject(message, "There are No Questions to display");
		}
		return modelAndView;
	}

	@PostMapping(value = "createQuestion")
	public ModelAndView createQuestion(QuestionDto questionDto) {
		ModelAndView modelAndView = new ModelAndView(questionLibraryOperationStatus);
		Question savedQuestion = questionLibraryServiceImpl.createQuestion(questionDto);
		format = String.format("Question with id: %d created successfully", savedQuestion.getId());
		modelAndView.addObject(message, format);
		return modelAndView;
	}

//	@PostMapping(value = "updateTitle")
//	public ModelAndView updateTitle(@RequestParam("id") int id, @RequestParam("title") String title) {
//		ModelAndView modelAndView = new ModelAndView(updationStatus);
//		Question updatedQuestion;
//		try {
//			updatedQuestion = questionLibraryServiceImpl.updateTitle(id, title);
//			format = String.format("Question title with id: %d updated successfully", updatedQuestion.getId());
//			modelAndView.addObject(message, format);
//		} catch (QuestionNotFoundException e) {
//			modelAndView.addObject(message, e.getMessage());
//		}
//		return modelAndView;
//	}
//
//	@PostMapping(value = "updateTag")
//	public ModelAndView updateTag(@RequestParam("id") int id, @RequestParam("tag") String tag) {
//		ModelAndView modelAndView = new ModelAndView(updationStatus);
//		Question updatedQuestion;
//		try {
//			updatedQuestion = questionLibraryServiceImpl.updateTag(id, tag);
//			format = String.format("Question tag with id: %d updated successfully", updatedQuestion.getId());
//			modelAndView.addObject(message, format);
//		} catch (QuestionNotFoundException e) {
//			modelAndView.addObject(message, e.getMessage());
//		}
//		return modelAndView;
//	}
//
//	@PostMapping(value = "updateDifficultyLevel")
//	public ModelAndView updateDifficultyLevel(@RequestParam("id") int id,
//			@RequestParam("difficultyLevel") String difficultyLevel) {
//		ModelAndView modelAndView = new ModelAndView(updationStatus);
//		Question updatedQuestion;
//		try {
//			updatedQuestion = questionLibraryServiceImpl.updateDifficulty(id, difficultyLevel);
//			format = String.format("Question difficultyLevel with id: %d updated successfully",
//					updatedQuestion.getId());
//			modelAndView.addObject(message, format);
//		} catch (QuestionNotFoundException e) {
//			modelAndView.addObject(message, e.getMessage());
//		}
//		return modelAndView;
//	}
//
//	@PostMapping(value = "updateAnswer")
//	public ModelAndView updateAnswer(@RequestParam("id") int id, @RequestParam("answer") String answer) {
//		ModelAndView modelAndView = new ModelAndView(updationStatus);
//		Question updatedQuestion;
//		try {
//			updatedQuestion = questionLibraryServiceImpl.updateAnswer(id, answer);
//			format = String.format("Question answer with id: %d updated successfully", updatedQuestion.getId());
//			modelAndView.addObject(message, format);
//		} catch (QuestionNotFoundException e) {
//			modelAndView.addObject(message, e.getMessage());
//		}
//		return modelAndView;
//	}
//
//	@PostMapping(value = "updateOption")
//	public ModelAndView updateOption(@RequestParam("id") int id, @RequestParam("index") int index,
//			@RequestParam("value") String value) {
//		ModelAndView modelAndView = new ModelAndView(updationStatus);
//		Question updatedQuestion = new Question();
//		try {
//			updatedQuestion = questionLibraryServiceImpl.updateOptions(id, index, value);
//			format = String.format("Question option with id: %d updated successfully", updatedQuestion.getId());
//			modelAndView.addObject(message, format);
//		} catch (QuestionNotFoundException | IndexOutOfBoundsException e) {
//			modelAndView.addObject(message, e.getMessage());
//		}
//		return modelAndView;
//	}

}
