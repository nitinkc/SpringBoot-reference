package com.springHelloWorld.exception;

import com.springHelloWorld.exception.business.DbDownException;
import com.springHelloWorld.exception.business.SomeBusinessException;
import com.springHelloWorld.exception.business.StudentNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Date;

import static com.springHelloWorld.exception.ErrorCodes.*;

@RestControllerAdvice
@Slf4j
@Getter
@Setter
public class GlobalExceptionHandler {

    @ExceptionHandler(value
            = {IllegalArgumentException.class,})
    @ResponseStatus(HttpStatus.I_AM_A_TEAPOT)
    public ResponseEntity<String> handleStudentNotFound(StudentNotFoundException exception) {
        log.error("Handling Exception... Caught exception: {}", ExceptionUtils.getStackTrace(exception));

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(exception.getMessage());
    }

    @ExceptionHandler(value = {StudentNotFoundException.class})
    @ResponseStatus(HttpStatus.I_AM_A_TEAPOT)
    public ExceptionResponse handleStudentExceptions(
            StudentNotFoundException exception, final HttpServletRequest request) {
        ExceptionResponse error = ExceptionResponse.builder()
                .errorMessage(exception.getMessage())
                .requestedURI(request.getRequestURI())
                .exceptionType(exception.getClass().getSimpleName())
                .methodName(request.getMethod())
                .errorCode(ERR_122.getErrorCode()+" :: "+ERR_122.getErrorMessage())
                .thrownByMethod(exception.getStackTrace()[0].getMethodName())
                .thrownByClass(exception.getStackTrace()[0].getClassName())
                .build();

        return error;
    }

    @ExceptionHandler(value = {DbDownException.class})
    @ResponseStatus(HttpStatus.I_AM_A_TEAPOT)
    public @ResponseBody ExceptionResponse handleDbExceptions(
            DbDownException exception, final HttpServletRequest request) {
        ExceptionResponse error = ExceptionResponse.builder()
                .errorMessage(exception.getMessage())
                .exceptionType(exception.getClass().getSimpleName())
                .requestedURI(request.getRequestURI())
                .methodName(request.getMethod())
                .errorCode(ERR_123.getErrorCode()+" :: "+ERR_123.getErrorMessage())
                .thrownByMethod(exception.getStackTrace()[0].getMethodName())
                .thrownByClass(exception.getStackTrace()[0].getClassName())
                .build();
        return error;
    }

    @ExceptionHandler(value = {SomeBusinessException.class})
    @ResponseStatus(value = HttpStatus.UNAVAILABLE_FOR_LEGAL_REASONS)
    public @ResponseBody TraceableError handleDbExceptions(
            SomeBusinessException exception, final HttpServletRequest request) {
        TraceableError traceableError = TraceableError.builder()
                .exceptionType(exception.getClass().getSimpleName())
                .exceptionMessage(exception.getMessage())
                .errorTitle(ERR_124.getCode())
                .timeStamp(new Date())
                .errorDescription(ERR_124.getErrorMessage())
                .path(request.getRequestURI())
                .errorCode(ERR_124.getErrorCode())
                .statusCode(HttpStatus.UNAVAILABLE_FOR_LEGAL_REASONS)
                .thrownByMethod(exception.getStackTrace()[0].getMethodName())
                .thrownByClass(exception.getStackTrace()[0].getClassName())
                .build();
        return traceableError;
    }
}
