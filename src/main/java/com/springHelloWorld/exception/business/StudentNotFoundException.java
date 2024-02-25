package com.springHelloWorld.exception.business;

//@ResponseStatus(HttpStatus.I_AM_A_TEAPOT)
//@ResponseStatus(HttpStatus.NO_CONTENT)
public class StudentNotFoundException extends RuntimeException {
    public StudentNotFoundException(String message) {
        super(message);
    }
}