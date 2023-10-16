package com.blog.exceptions;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandlar {
    @ExceptionHandler(ResourceNotFoundException.class)
    public final ResponseEntity<?> handleProjectIdException(ResourceNotFoundException ex, WebRequest request){
    	ExceptionDetails exceptionResponse = new ExceptionDetails(new Date(), ex.getMessage(), request.getDescription(true));
        return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);

}
}
