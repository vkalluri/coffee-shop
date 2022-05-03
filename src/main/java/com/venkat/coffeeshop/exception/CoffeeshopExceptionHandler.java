package com.venkat.coffeeshop.exception;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
@Service
public class CoffeeshopExceptionHandler extends ResponseEntityExceptionHandler{
	
	@ExceptionHandler(CoffeeshopException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	protected ResponseEntity<Object> handleException(final CoffeeshopException exception){
		return buildResponseEntity(exception.getMessage());
	}
	
	private ResponseEntity<Object> buildResponseEntity(final String errorMessage){
		return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
	}

}
