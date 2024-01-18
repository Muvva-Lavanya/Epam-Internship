package com.epam.restapi;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.epam.dto.QuestionDto;
import com.epam.entity.Question;
import com.epam.service.QuestionLibraryServiceImpl;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("questions")
@Slf4j
public class QuestionRestController {
	@Autowired
	QuestionLibraryServiceImpl questionLibraryServiceImpl;
	@PostMapping
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<QuestionDto> createQuestion(@Valid @RequestBody QuestionDto questionDto) {
		log.info("Received post request to create Question ");
		return new ResponseEntity<>(questionLibraryServiceImpl.createQuestion(questionDto),HttpStatus.CREATED);
	}

	@GetMapping("/{id}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<QuestionDto> getQuestionById(@PathVariable int id) {
		log.info("Received get request to view question by id");
		return new ResponseEntity<>(questionLibraryServiceImpl.getQuestionById(id),HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	@PreAuthorize("hasAuthority('ADMIN')")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void delete(@PathVariable int id) throws DataIntegrityViolationException { 
		log.info("Received delete request to delete Question");
		questionLibraryServiceImpl.deleteQuestion(id);
	}

	@GetMapping()
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<List<Question>> getAllQuestions() {
		log.info("Received get request to view all Questions");
		return new ResponseEntity<>(questionLibraryServiceImpl.getQuestions(), HttpStatus.OK);
	}

	@PutMapping
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<QuestionDto> update(@Valid @RequestBody QuestionDto questionDto) {
		log.info("Received put request to update Question");
		return new ResponseEntity<>(questionLibraryServiceImpl.updateQuestion(questionDto),HttpStatus.OK);
	}

}
