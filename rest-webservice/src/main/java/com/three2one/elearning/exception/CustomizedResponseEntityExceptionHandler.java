package com.three2one.elearning.exception;

import java.util.Date;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@RestController
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(Exception.class)
	public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) {
		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(),
				request.getDescription(false));
		return new ResponseEntity(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(StudentNotFoundException.class)
	public final ResponseEntity<Object> handleUserNotFoundException(StudentNotFoundException ex, WebRequest request) {
		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(),
				request.getDescription(false));
		return new ResponseEntity(exceptionResponse, HttpStatus.NOT_FOUND);
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), "Validation Failed",
				ex.getBindingResult().toString());
		return new ResponseEntity(exceptionResponse, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler({ TransactionSystemException.class })
	public ResponseEntity<Object> handleConstraintViolation(Exception ex, WebRequest request) {
	    Throwable cause = ((TransactionSystemException) ex).getRootCause();
	    if (cause instanceof ConstraintViolationException) {
	        Set<ConstraintViolation<?>> constraintViolations = ((ConstraintViolationException) cause).getConstraintViolations();
	        String message = "";
	        for(ConstraintViolation constraintViolation : constraintViolations) {
	        	message = message + constraintViolation.getMessage()+", ";
	        }  
	        ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), message,
					request.getDescription(false));
	        return new ResponseEntity(exceptionResponse, HttpStatus.BAD_REQUEST);
	    
	    }
	    return new ResponseEntity(new ExceptionResponse(new Date(), ex.getMessage(), request.getDescription(false)), HttpStatus.BAD_REQUEST);
	}
}