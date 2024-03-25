package com.spring.reference.exception.handlers;

import com.spring.reference.exception.ErrorCodes;
import com.spring.reference.exception.MyExceptionResponse;
import com.spring.reference.exception.business.BadInputException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolationException;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

/**
 * Created by nichaurasia on Sunday, December/08/2019 at 12:16 AM
 */

@ControllerAdvice
@Slf4j
@Order(Ordered.HIGHEST_PRECEDENCE) // Set the highest precedence
public class ValidationExceptionHandler {

    @ExceptionHandler(value = {IllegalArgumentException.class,})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Object> handleStudentNotFound(Exception exception) {
        log.info("Handling Exception... Caught exception: {}", ExceptionUtils.getStackTrace(exception));

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(exception.getMessage());
    }

    @ExceptionHandler(BadInputException.class)
    public ResponseEntity<Object> handleAllExceptions(BadInputException ex, WebRequest request) {
        String errorMessage = " An error occurred:"  + ex.getMessage() + "\n" +
                "With description :: " + request.getDescription(true);


        return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<MyExceptionResponse> handleValidationExceptions(MethodArgumentNotValidException ex, final HttpServletRequest request) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        MyExceptionResponse myExceptionResponse = MyExceptionResponse.builder()
                .from("ExceptionResponse from MethodArgumentNotValidException")
                .timestamp(ZonedDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS a z(O)")))
                //.exceptionType(ex.getBindingResult().toString())
                .errorMessage(errors.toString())
                .requestedURI(request.getRequestURI())
                .methodName(request.getMethod())
                .errorCode(ErrorCodes.ERR_140.getErrorCode()+" :: "+ ErrorCodes.ERR_140.getErrorMessage())
                .thrownByMethod(ex.getStackTrace()[0].getMethodName())
                .thrownByClass(ex.getStackTrace()[0].getClassName())
                .build();

        return ResponseEntity.badRequest().body(myExceptionResponse);
    }

    @ExceptionHandler(BindException.class)
    public ResponseEntity<Map<String, String>> handleBindExceptions(BindException ex) {
        BindingResult result = ex.getBindingResult();
        Map<String, String> errors = new HashMap<>();
        result.getFieldErrors().forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));
        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    protected ResponseEntity<MyExceptionResponse> handleRequestParamNotValid(Exception exception, final HttpServletRequest request) {

        MyExceptionResponse error = MyExceptionResponse.builder()
                .from("Validation Exception Response from handleRequestParamNotValid :: ConstraintViolationException")
                .errorMessage(exception.getMessage())
                .requestedURI(request.getRequestURI())
                .exceptionType(exception.getClass().getSimpleName())
                .methodName(request.getMethod())
                .errorCode(ErrorCodes.ERR_122.getErrorCode()+" :: "+ ErrorCodes.ERR_122.getErrorMessage())
                .thrownByMethod(exception.getStackTrace()[0].getMethodName())
                .thrownByClass(exception.getStackTrace()[0].getClassName())
                .timestamp(ZonedDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS a z(O)")))
                .build();

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
}