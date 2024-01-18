package com.epam.customexception;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice
@Slf4j
public class ExceptionalHandler {
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ExceptionResponse handleMethodArgumentNotValidException(MethodArgumentNotValidException e,
			WebRequest request) {
		List<String> error = new ArrayList<>();
		e.getAllErrors().forEach(err -> error.add(err.getDefaultMessage()));
		log.error("Encountered MethodArgumentNotValidException:{}",ExceptionUtils.getStackTrace(e).getClass());
		return new ExceptionResponse(new Date().toString(), HttpStatus.BAD_REQUEST.toString(), error.toString(),
				request.getDescription(false));

	}

	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	public ExceptionResponse handleMethodArgumentMismatchException(MethodArgumentTypeMismatchException e,
			WebRequest request) {
		log.error("Encountered MethodArgumentTypeMismatchException:{}",ExceptionUtils.getStackTrace(e));
		return new ExceptionResponse(new Date().toString(), HttpStatus.BAD_REQUEST.toString(), e.getMessage(),
				request.getDescription(false));

	}

	@ExceptionHandler(QuestionException.class)
	public ExceptionResponse handleElementNotFoundException(QuestionException e, WebRequest request) {
		log.error("Encountered QuestionException :{}",e.getMessage());
		return new ExceptionResponse(new Date().toString(), HttpStatus.OK.toString(), e.getMessage(),
				request.getDescription(false));

	}

	@ExceptionHandler(QuizException.class)
	public ExceptionResponse handleInsufficientQuestionException(QuizException e, WebRequest request) {
		log.error("Encountered QuizException :{}",e.getMessage());
		return new ExceptionResponse(new Date().toString(), HttpStatus.OK.toString(), e.getMessage(),
				request.getDescription(false));

	}

	@ExceptionHandler(UserException.class)
	public ExceptionResponse handleAuthenticationFailureException(UserException e, WebRequest request) {
		log.error("Encountered UserException :{}",e.getMessage());
		return new ExceptionResponse(new Date().toString(), HttpStatus.OK.toString(), e.getMessage(),
				request.getDescription(false));

	}

	@ExceptionHandler(DataIntegrityViolationException.class)
	public ExceptionResponse handleDataIntegrityException(DataIntegrityViolationException e, WebRequest request) {
		log.error("Encountered DataIntegrityViolationException :{}",e.getMessage());
		return new ExceptionResponse(new Date().toString(), HttpStatus.OK.toString(),
				"question is present in quiz cannot delete",
				request.getDescription(true));

	}
	
	@ExceptionHandler(RuntimeException.class)
	public ExceptionResponse handleRunTimeException(RuntimeException e, WebRequest request) {
		log.error("Encountered RuntimeException :{}",e.getMessage());
		return new ExceptionResponse(new Date().toString(), HttpStatus.INTERNAL_SERVER_ERROR.toString(), e.getMessage(),
				request.getDescription(false));

	}

}
