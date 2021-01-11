package com.kalah.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * @author Ankit
 * @Description Controller for Kalah Exception, all the exception within the
 *              application will be routed through this class.
 */
@ControllerAdvice
public class KalahExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(KalahException.class)
	public final ResponseEntity<Object> handleKalahException(final KalahException e) {
		ExceptionResponse response = ExceptionResponse.of(e.getMessage(), HttpStatus.BAD_REQUEST);
		return ResponseEntity.status(response.getStatus()).body(response);
	}
}