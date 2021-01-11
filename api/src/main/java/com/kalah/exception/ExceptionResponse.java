package com.kalah.exception;

import org.springframework.http.HttpStatus;

/**
 * @author Ankit
 * @Description Model for Exception Class
 * 
 *
 */
public class ExceptionResponse {

	private final String message;
	private final HttpStatus status;

	public String getMessage() {
		return message;
	}

	public HttpStatus getStatus() {
		return status;
	}

	public ExceptionResponse(String message, HttpStatus status) {
		this.status = status;
		this.message = message;
	}

	public static ExceptionResponse of(final String message, HttpStatus status) {
		return new ExceptionResponse(message, status);
	}

}
