package com.example.demo.middleware;

import com.example.demo.dto.ErrorResponse;
import com.example.demo.exceptions.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(NotFoundException.class)
	public ResponseEntity<ErrorResponse> handleItemNotFound(NotFoundException ex) {
		ErrorResponse error = new ErrorResponse(
				HttpStatus.NOT_FOUND.value(),
				ex.getMessage()
		);
		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
	}

}
