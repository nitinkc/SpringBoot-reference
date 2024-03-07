package com.spring.reference.exception.business;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)//Seems Optional, the one in the Global exceptional handler takes precedence
public class DbDownException extends RuntimeException {
    public DbDownException(String message) {
        super(message);
    }
}
