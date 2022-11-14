package com.masai.exceptions;

import java.time.LocalDateTime;

import javax.persistence.RollbackException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
public class GlobalExceptionHandler {

	
	
	
	
	@ExceptionHandler(CustomerException.class)
	public ResponseEntity<MyErrorDetails> customerExceptionHandler(CustomerException se, WebRequest req){
		
		
		MyErrorDetails err= new MyErrorDetails();
			err.setTimestamp(LocalDateTime.now());
			err.setMessage(se.getMessage());
			err.setDetails(req.getDescription(false));
				
		return new ResponseEntity<MyErrorDetails>(err, HttpStatus.NOT_FOUND);
		
	}
	
	
	@ExceptionHandler(LoginException.class)
	public ResponseEntity<MyErrorDetails> loginExceptionHandler(LoginException se, WebRequest req){
		
		
		MyErrorDetails err= new MyErrorDetails();
			err.setTimestamp(LocalDateTime.now());
			err.setMessage(se.getMessage());
			err.setDetails(req.getDescription(false));
				
		return new ResponseEntity<MyErrorDetails>(err, HttpStatus.BAD_REQUEST);
		
	}
	
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<MyErrorDetails> otherExceptionHandler(Exception se, WebRequest req){
		
		
		MyErrorDetails err= new MyErrorDetails();
			err.setTimestamp(LocalDateTime.now());
			err.setMessage(se.getMessage());
			err.setDetails(req.getDescription(false));
				
		return new ResponseEntity<MyErrorDetails>(err, HttpStatus.INTERNAL_SERVER_ERROR);
		
	}
	
	/////////////////////////////////////////////
	
	
	
	// exceptions for plant module
	
	@ExceptionHandler(PlantNotFoundException.class)
    public ResponseEntity<MyErrorDetails> plantNotfoundExceptionHandler(PlantNotFoundException pnfe, WebRequest req ){
	   
		MyErrorDetails err=new MyErrorDetails();
		err.setTimestamp(LocalDateTime.now());
		err.setDetails(req.getDescription(false));
		err.setMessage(pnfe.getMessage());
		
		return new ResponseEntity<MyErrorDetails>(err,HttpStatus.BAD_REQUEST);
    }
	
	
	@ExceptionHandler(SeedNotFoundException.class)
    public ResponseEntity<MyErrorDetails> seedNotfoundExceptionHandler(SeedNotFoundException snfe, WebRequest req ){
	   
		MyErrorDetails err=new MyErrorDetails();
		err.setTimestamp(LocalDateTime.now());
		err.setDetails(req.getDescription(false));
		err.setMessage(snfe.getMessage());
		
		return new ResponseEntity<MyErrorDetails>(err,HttpStatus.BAD_REQUEST);
    }
	
	@ExceptionHandler(InvalidKeyException.class)
    public ResponseEntity<MyErrorDetails> invalidKeyExceptionHandler(InvalidKeyException keyex, WebRequest req ){
	   
		MyErrorDetails err=new MyErrorDetails();
		err.setTimestamp(LocalDateTime.now());
		err.setDetails(req.getDescription(false));
		err.setMessage(keyex.getMessage());
		
		return new ResponseEntity<MyErrorDetails>(err,HttpStatus.BAD_REQUEST);
    }
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<MyErrorDetails> invalidMethodArgumentExceptionHandler(MethodArgumentNotValidException error ){
	   
		MyErrorDetails err=new MyErrorDetails();
		err.setTimestamp(LocalDateTime.now());
		err.setMessage("Invalid Argument: Please pass the valid argument.");
		err.setDetails(error.getBindingResult().getFieldError().getDefaultMessage());
		
		return new ResponseEntity<MyErrorDetails>(err,HttpStatus.FORBIDDEN);
    }
	

	@ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<MyErrorDetails> noHandlerExceptionHandler(NoHandlerFoundException error, WebRequest wr ){
	   
	  System.out.println("Inside No handler Exceptionsin : Globle exception handling");
	  MyErrorDetails err=new MyErrorDetails(LocalDateTime.now(),error.getMessage(), wr.getDescription(false));
		
	  return new ResponseEntity<MyErrorDetails>(err,HttpStatus.NOT_FOUND);
    }
	
	
		
	@ExceptionHandler(RollbackException.class)
	public ResponseEntity<MyErrorDetails> handleRollbackException(RollbackException exp, WebRequest req) {
		MyErrorDetails err = new MyErrorDetails(LocalDateTime.now(),
				"Improper arguments passed in json: Validation failed", req.getDescription(false));

		return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
	}
	
	
	
	
	
	
}
