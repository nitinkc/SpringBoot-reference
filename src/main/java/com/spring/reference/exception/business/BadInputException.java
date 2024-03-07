package com.spring.reference.exception.business;

public class BadInputException extends RuntimeException {
    public BadInputException(String message) {
        super(message);
    }
}
