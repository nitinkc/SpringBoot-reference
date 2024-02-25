package com.springHelloWorld.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

/**
 * Created by nichaurasia on Sunday, December/08/2019 at 12:16 AM
 */

@RestController
@ControllerAdvice
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> handleAllException(Exception ex, WebRequest request){
        ExceptionResponse exceptionResponse = ExceptionResponse.builder()
                .timeStamp(new Date())
                .errorMessage(ex.getMessage())
                .exceptionType(request.getDescription(false))
                .build();

        return  new ResponseEntity(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    //Exception for Failed Validations
//    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request){

        ExceptionResponse exceptionResponse = ExceptionResponse.builder()
                .timeStamp(new Date())
                .errorMessage("Validation Failed")
                .exceptionType(ex.getBindingResult().toString())
                .build();

        return  new ResponseEntity(exceptionResponse, HttpStatus.BAD_REQUEST);
    }
}
