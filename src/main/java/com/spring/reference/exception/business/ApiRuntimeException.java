package com.spring.reference.exception.business;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@RequiredArgsConstructor
public class ApiRuntimeException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    private String correlationId;
    private HttpStatus statusCode;
    private String thrownByMethod;
    private String[] thrownByMethodArgs;

    public ApiRuntimeException(Throwable cause) {
        super(cause);
    }
    public ApiRuntimeException(String statusMessage) {
        super(statusMessage);
    }
    public ApiRuntimeException(String statusMessage, Exception e) {
        super(statusMessage, e);
    }

    public ApiRuntimeException(HttpStatus statusCode, String statusMessage) {
        super(statusMessage);
        this.statusCode = statusCode;
    }

    public ApiRuntimeException(HttpStatus statusCode, String statusMessage, Exception e) {
        super(statusMessage, e);
        this.statusCode = statusCode;
    }

    public ApiRuntimeException(final HttpStatus statusCode, final String statusMessage, final String thrownByMethod,
                               final String[] thrownByMethodArgs) {
        super(statusMessage);
        this.statusCode = statusCode;
        this.thrownByMethod = thrownByMethod;
        this.thrownByMethodArgs = thrownByMethodArgs;
    }

    public ApiRuntimeException(final HttpStatus statusCode, final String statusMessage, final String thrownByMethod,
                               final String[] thrownByMethodArgs, final Exception e) {
        super(statusMessage, e);
        this.statusCode = statusCode;
        this.thrownByMethod = thrownByMethod;
        this.thrownByMethodArgs = thrownByMethodArgs;
    }
}
