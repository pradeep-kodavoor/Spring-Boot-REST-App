package com.practice.springboot.basics.SpringBootRESTApp.util;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.practice.springboot.basics.SpringBootRESTApp.domain.ErrorDetails;

@ControllerAdvice
@RestController
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

	Logger logger = LoggerFactory.getLogger(CustomizedResponseEntityExceptionHandler.class);

	@ExceptionHandler(Exception.class)
	public final ResponseEntity<ErrorDetails> handleGenericException(Exception e, WebRequest request) {
		System.out.println("!!!!!!!!!!!!Generic Excpetion Caught");
		ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(), e.getMessage(),
				request.getDescription(false));
		return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(BookNotFoundException.class)
	public final ResponseEntity<ErrorDetails> handleBookNotFoundException(BookNotFoundException e, WebRequest request) {
		System.out.println("!!!!!!!!!!!!BookNotFoundException Excpetion Caught");
		ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(), e.getMessage(),
				request.getDescription(false));
		return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
	}


	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		System.out.println("!!!!!!!!!!!!Binding Result: " + ex.getBindingResult().getAllErrors().toString());
		List<String> errors = new ArrayList<>();
		for (ObjectError error : ex.getBindingResult().getAllErrors()) {
			errors.add(error.getDefaultMessage());
		}

		ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(), "Bean validation failure",
				ex.getBindingResult().getAllErrors().get(0).getDefaultMessage().toString());

		return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
	}


}
