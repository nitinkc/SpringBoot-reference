package com.spring.reference.exception;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class MyExceptionResponse {
    private String from;
    private String errorCode;
    private String errorMessage;
    private String methodName;
    private String requestedURI;
    private String thrownByMethod;
    private String thrownByClass;
    private String exceptionType;
    private String timestamp;
}
