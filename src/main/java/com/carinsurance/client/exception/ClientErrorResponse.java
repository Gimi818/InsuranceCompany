package com.carinsurance.client.exception;

import org.springframework.http.HttpStatus;

public record ClientErrorResponse(String message, HttpStatus status) {
}
