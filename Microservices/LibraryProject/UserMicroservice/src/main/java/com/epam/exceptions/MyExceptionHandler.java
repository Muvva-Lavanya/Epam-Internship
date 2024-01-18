package com.epam.exceptions;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice
@Slf4j
public class MyExceptionHandler {
	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public ExceptionResponse handleMethodArgumentNotValidException(MethodArgumentNotValidException e,
			WebRequest request) {
		List<String> error = new ArrayList<>();
		e.getAllErrors().forEach(err -> error.add(err.getDefaultMessage()));
		log.error("Encountered MethodArgumentNotValidException:{}",ExceptionUtils.getStackTrace(e));
		return new ExceptionResponse(new Date().toString(), HttpStatus.BAD_REQUEST.toString(), error.toString(),
				request.getDescription(false));

	}
 
	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public ExceptionResponse handleMethodArgumentMismatchException(MethodArgumentTypeMismatchException e,
			WebRequest request) {
		log.error("Encountered MethodArgumentTypeMismatchException:{}",ExceptionUtils.getStackTrace(e));
		return new ExceptionResponse(new Date().toString(), HttpStatus.BAD_REQUEST.toString(), e.getMessage(),
				request.getDescription(false));

	}

	
	@ExceptionHandler(DataIntegrityViolationException.class)
	public ExceptionResponse handleDataIntegrityException(DataIntegrityViolationException e, WebRequest request) {
		log.error("Encountered DataIntegrityViolationException :{}",ExceptionUtils.getStackTrace(e));
		return new ExceptionResponse(new Date().toString(), HttpStatus.OK.toString(),"USername/email already exists, please try with different ones",
				request.getDescription(true));

	}
	
	@ExceptionHandler(RuntimeException.class)
	public ExceptionResponse handleRunTimeException(RuntimeException e, WebRequest request) {
		log.error("Encountered RuntimeException :{}",ExceptionUtils.getStackTrace(e));
		return new ExceptionResponse(new Date().toString(), HttpStatus.INTERNAL_SERVER_ERROR.toString(), e.getMessage(),
				request.getDescription(false));

	}
	
}
