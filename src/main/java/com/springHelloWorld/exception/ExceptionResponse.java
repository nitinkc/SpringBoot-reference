package com.springHelloWorld.exception;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Builder
public class ExceptionResponse {
    private String errorCode;
    private String errorMessage;
    private String methodName;
    private String requestedURI;
    private String thrownByMethod;
    private String thrownByClass;
    private String exceptionType;
    private Date timeStamp;
}
