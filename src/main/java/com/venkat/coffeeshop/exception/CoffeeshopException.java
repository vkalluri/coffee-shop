package com.venkat.coffeeshop.exception;

import org.springframework.http.HttpStatus;

import lombok.Getter;

public class CoffeeshopException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Getter
	private final String message;
	
	@Getter
	private final HttpStatus httpStatus;
	
	public CoffeeshopException(String message, HttpStatus httpStatus) {
		this.message = message;
		this.httpStatus = httpStatus;
	}

}
