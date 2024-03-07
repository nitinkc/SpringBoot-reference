package com.spring.reference.exception.business;

//@ResponseStatus(HttpStatus.UNAVAILABLE_FOR_LEGAL_REASONS)//Seems Optional, the one in the Global exceptional handler takes precedence
public class TraceableBusinessException extends BusinessException {
    public TraceableBusinessException(String message) {
        super(message);
    }
}