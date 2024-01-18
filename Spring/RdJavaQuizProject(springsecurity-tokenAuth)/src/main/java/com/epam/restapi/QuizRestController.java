package com.epam.restapi;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.epam.dto.QuizDto;
import com.epam.entity.Quiz;
import com.epam.service.QuizLibraryServiceImpl;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("quizzes")
@Slf4j
public class QuizRestController {
	@Autowired
	QuizLibraryServiceImpl quizLibraryServiceImpl;

	@PostMapping
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<QuizDto> createQuiz(@Valid @RequestBody QuizDto quizDto,
			@RequestParam("questionIds") List<Integer> questionIds) {
		log.info("Received post request to create Quiz");
		return new ResponseEntity<>(quizLibraryServiceImpl.addQuiz(quizDto, questionIds), HttpStatus.CREATED);

	}

	@GetMapping("/{id}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<QuizDto> getQuizById(@PathVariable int id) {
		log.info("Received get request to view quiz by id");
		return new ResponseEntity<>(quizLibraryServiceImpl.getQuizById(id), HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<QuizDto> deleteQuiz(@PathVariable int id) {
		log.info("Received delete request to delete Quiz");
		quizLibraryServiceImpl.deleteQuiz(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@GetMapping
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<List<Quiz>> getAllQuizzes() {
		log.info("Received get request to view all quizzes");
		return new ResponseEntity<>(quizLibraryServiceImpl.getAllQuizzes(), HttpStatus.OK);
	}

	@PutMapping
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<QuizDto> updateQuiz(@Valid @RequestBody QuizDto quizDto,
			@RequestParam("questionIds") List<Integer> questionIds) {
		log.info("Received put request to update Quiz");
		return new ResponseEntity<>(quizLibraryServiceImpl.updateQuiz(quizDto, questionIds), HttpStatus.OK);
	}
}
