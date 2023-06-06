package com.carinsurance.car.exception;

public class CarHasAPolicyException extends RuntimeException {

    public CarHasAPolicyException(Long id) {
        super(String.format("Car with id %d already has a policy assigned", id));
    }
}
