package com.springHelloWorld.exception.business;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)//Seems Optional, the one in the Global exceptional handler takes precedence
public class DbDownException extends RuntimeException {
    public DbDownException(String message) {
        super(message);
    }
}
