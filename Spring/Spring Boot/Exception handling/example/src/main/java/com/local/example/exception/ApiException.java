package com.local.example.exception;

import java.time.ZonedDateTime;
import org.springframework.http.HttpStatus;

public class ApiException {

    private final String msg;
    private Throwable throwable;  //This field could be removed.
    private final HttpStatus httpStatus;
    private final ZonedDateTime timestamp;

    public ApiException(String msg,
                        Throwable throwable,
                        HttpStatus httpStatus,
                        ZonedDateTime timestamp) {
        this(msg, httpStatus, timestamp);  //Constructor chaining.
        this.throwable = throwable;
    }

    public ApiException(String msg,
                        HttpStatus httpStatus,
                        ZonedDateTime timestamp) {
        this.msg = msg;
        this.httpStatus = httpStatus;
        this.timestamp = timestamp;
    }

    public String getMsg() {
        return msg;
    }

    public Throwable getThrowable() {
        return throwable;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public ZonedDateTime getTimestamp() {
        return timestamp;
    }
}
