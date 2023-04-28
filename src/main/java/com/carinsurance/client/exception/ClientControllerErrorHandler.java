package com.carinsurance.client.exception;

import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
@Log4j2
public class ClientControllerErrorHandler {
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ClientNotFoundException.class)
    @ResponseBody
    public ClientErrorResponse clientNotFound(ClientNotFoundException exception) {
        final String message = exception.getMessage();
        log.error(message);
        return new
                ClientErrorResponse(message, HttpStatus.NOT_FOUND);
    }
}
