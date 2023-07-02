package com.jbk.productApi.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(ProductAlreadyExistException.class)
	public ResponseEntity<String> productAlreadyExistException(ProductAlreadyExistException ex){
		String msg=ex.getMessage();
		return new  ResponseEntity<String>(msg,HttpStatus.OK);
	}
	@ExceptionHandler(ProductNotFoundException.class)
	public ResponseEntity<String> productNotFoundException(ProductNotFoundException ex){
		String message=ex.getMessage();
		return new ResponseEntity<String>(message,HttpStatus.OK);
	}
	
	@ExceptionHandler(NullPointerExceptionoccurs.class)
	public ResponseEntity<String> nullPointerException(NullPointerExceptionoccurs ex){
		String mssg=ex.getMessage();
		return new ResponseEntity<String>(mssg,HttpStatus.OK);
	}
}
