package com.epam.restapi;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.epam.dto.QuestionDto;
import com.epam.entity.Question;
import com.epam.service.QuestionLibraryServiceImpl;

import jakarta.validation.Valid;

@RestController
@RequestMapping("questions")
public class QuestionRestController {
	@Autowired
	QuestionLibraryServiceImpl questionLibraryServiceImpl;
	Logger logger = LogManager.getLogger(QuestionRestController.class);
	@PostMapping
	public ResponseEntity<QuestionDto> createQuestion(@Valid @RequestBody QuestionDto questionDto) {
		logger.info("Received post request to create Question");
		return new ResponseEntity<>(questionLibraryServiceImpl.createQuestion(questionDto),HttpStatus.CREATED);
	}

	@GetMapping("/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<QuestionDto> getQuestionById(@PathVariable int id) {
		logger.info("Received get request to view question by id");
		return new ResponseEntity<>(questionLibraryServiceImpl.getQuestionById(id),HttpStatus.OK);
	}
 
	@DeleteMapping("/{id}")
	public ResponseEntity<QuestionDto> delete(@PathVariable int id) throws DataIntegrityViolationException {
		logger.info("Received delete request to delete Question");
		questionLibraryServiceImpl.deleteQuestion(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<List<Question>> getAllQuestions() {
		logger.info("Received get request to view all Questions");
		return new ResponseEntity<>(questionLibraryServiceImpl.getQuestions(), HttpStatus.OK);
	}

	@PutMapping
	public ResponseEntity<QuestionDto> update(@Valid @RequestBody QuestionDto questionDto) {
		logger.info("Received put request to update Question");
		return new ResponseEntity<>(questionLibraryServiceImpl.updateQuestion(questionDto),HttpStatus.OK);
	}

}
