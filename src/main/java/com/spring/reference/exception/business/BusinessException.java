package com.spring.reference.exception.business;

public class BusinessException extends RuntimeException {
    public BusinessException(String message) {
        super(message);
    }
}