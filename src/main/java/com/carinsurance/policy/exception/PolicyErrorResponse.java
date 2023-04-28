package com.carinsurance.policy.exception;

import org.springframework.http.HttpStatus;

public record PolicyErrorResponse(String message, HttpStatus status) {
}
