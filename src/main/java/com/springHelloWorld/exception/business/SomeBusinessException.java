package com.springHelloWorld.exception.business;

//@ResponseStatus(HttpStatus.UNAVAILABLE_FOR_LEGAL_REASONS)
public class SomeBusinessException extends RuntimeException {
    public SomeBusinessException(String message) {
        super(message);
    }
}
