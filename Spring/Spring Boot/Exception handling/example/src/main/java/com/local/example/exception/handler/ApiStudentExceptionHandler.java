package com.local.example.exception.handler;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import org.springframework.http.HttpStatus;
import com.local.example.exception.ApiException;
import com.local.example.exception.ApiRequestException;
import org.springframework.http.ResponseEntity;

@ControllerAdvice
public class ApiStudentExceptionHandler {

    @ExceptionHandler(ApiRequestException.class)  /*value = { ApiRequestException.class, OtherException.class }*/ //Here we could handle more than one exception. In this case, we are handling only ApiRequestException.
    public ResponseEntity<Object> handleApiRequestException(ApiRequestException e) {
        //1. Create a payload containing exception details
        HttpStatus status = HttpStatus.BAD_REQUEST;

        ApiException apiException = new ApiException(
                e.getMessage(),
                //e, //this field is unnecessary.
                status,
                ZonedDateTime.now(ZoneId.of("Z")));
        //2. Return response entity
        return new ResponseEntity<>(apiException, status);
    }

}
