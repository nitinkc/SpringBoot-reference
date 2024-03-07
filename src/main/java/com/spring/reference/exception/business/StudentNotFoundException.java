package com.spring.reference.exception.business;

//Seems Optional, the one in the Global exceptional handler takes precedence
//@ResponseStatus(HttpStatus.I_AM_A_TEAPOT)
//@ResponseStatus(HttpStatus.NO_CONTENT)
public class StudentNotFoundException extends BusinessException {
    public StudentNotFoundException(String message) {
        super(message);
    }
}