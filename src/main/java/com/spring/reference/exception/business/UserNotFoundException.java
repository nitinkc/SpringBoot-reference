package com.spring.reference.exception.business;

//@ResponseStatus(HttpStatus.NOT_FOUND)//Seems Optional, the one in the Global exceptional handler takes precedence
public class UserNotFoundException extends BusinessException {
    public UserNotFoundException(String message) {
        super(message);
    }
}