package com.spring.reference.exception.handlers;

import com.spring.reference.exception.ErrorCodes;
import com.spring.reference.exception.MyExceptionResponse;
import com.spring.reference.exception.TraceableErrorResponse;
import com.spring.reference.exception.business.*;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {


    //Handling 2 exception classes. Notice the parameter of handleNotFoundExceptions method (BusinessException exception)
    @ExceptionHandler(value = {UserNotFoundException.class, StudentNotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<MyExceptionResponse> handleNotFoundExceptions(BusinessException exception, final HttpServletRequest request) {
        MyExceptionResponse error = MyExceptionResponse.builder()
                .from("From Exception Response")
                .errorMessage(exception.getMessage())
                .requestedURI(request.getRequestURI())
                .exceptionType(exception.getClass().getSimpleName())
                .methodName(request.getMethod())
                .errorCode(ErrorCodes.ERR_122.getErrorCode()+" :: "+ ErrorCodes.ERR_122.getErrorMessage())
                .thrownByMethod(exception.getStackTrace()[0].getMethodName())
                .thrownByClass(exception.getStackTrace()[0].getClassName())
                .timestamp(ZonedDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS a z(O)")))
                .build();

        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {DbDownException.class})
    @ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)//the one in the Global exceptional handler takes precedence
    public @ResponseBody MyExceptionResponse handleDbExceptions(DbDownException exception,
                                                                final HttpServletRequest request) {
        MyExceptionResponse error = MyExceptionResponse.builder()
                .from("ExceptionResponse")
                .errorMessage(exception.getMessage())
                .exceptionType(exception.getClass().getSimpleName())
                .requestedURI(request.getRequestURI())
                .methodName(request.getMethod())
                .errorCode(ErrorCodes.ERR_123.getErrorCode()+" :: "+ ErrorCodes.ERR_123.getErrorMessage())
                .thrownByMethod(exception.getStackTrace()[0].getMethodName())
                .thrownByClass(exception.getStackTrace()[0].getClassName())
                .timestamp(ZonedDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS a z(O)")))
                .build();
        return error;
    }

    @ExceptionHandler(value = {TraceableBusinessException.class})
    @ResponseStatus(value = HttpStatus.UNAVAILABLE_FOR_LEGAL_REASONS)
    public @ResponseBody TraceableErrorResponse handleDbExceptions(
            TraceableBusinessException exception, final HttpServletRequest request) {
        TraceableErrorResponse traceableErrorResponse = TraceableErrorResponse.builder()
                .from("TraceableError")
                .exceptionType(exception.getClass().getSimpleName())
                .exceptionMessage(exception.getMessage())
                .errorTitle(ErrorCodes.ERR_124.getCode())
                .timestamp(ZonedDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS a z(O)")))
                .errorDescription(ErrorCodes.ERR_124.getErrorMessage())
                .path(request.getRequestURI())
                .errorCode(ErrorCodes.ERR_124.getErrorCode())
                .statusCode(HttpStatus.UNAVAILABLE_FOR_LEGAL_REASONS)
                .thrownByMethod(exception.getStackTrace()[0].getMethodName())
                .thrownByClass(exception.getStackTrace()[0].getClassName())
                .build();
        return traceableErrorResponse;
    }
}
