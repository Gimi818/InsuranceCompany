package com.carinsurance.car.exception;

import org.springframework.http.HttpStatus;

public record CarErrorResponse (String message, HttpStatus status) {
}
