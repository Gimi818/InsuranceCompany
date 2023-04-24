package com.carinsurance.polise.exception;

public class PolicyNotFoundException extends RuntimeException {
    public PolicyNotFoundException(Long id) {
        super(String.format("Policy with id %d not found", id));
    }
}
