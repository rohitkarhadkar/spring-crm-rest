package com.luv2code.springdemo.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomerRestExceptionHandler {

	//add exception handler for CustomerNotFoundException
	@ExceptionHandler
	public ResponseEntity<CustomerErrorResponse> handleException(CustomerNotFoundException exc)
	{
		//create the CustomerErrorResponse obj and fill the values
		CustomerErrorResponse error = new CustomerErrorResponse(
										  HttpStatus.NOT_FOUND.value(),
										  exc.getMessage(),
										  System.currentTimeMillis());
		
		//return the ResponseEntity<CustomerErrorResponse>
		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
	}
	
	//add exception handler for generic exception
	@ExceptionHandler
	public ResponseEntity<CustomerErrorResponse> handleException(Exception exc)
	{
		//create the CustomerErrorResponse obj and fill the values
		CustomerErrorResponse error = new CustomerErrorResponse(
										  HttpStatus.BAD_REQUEST.value(),
										  exc.getMessage(),
										  System.currentTimeMillis());
		
		//return the ResponseEntity<CustomerErrorResponse>
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}
	
	
}
























