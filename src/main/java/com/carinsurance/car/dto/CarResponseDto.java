package com.carinsurance.car.dto;

import com.carinsurance.car.CarModel;
import com.carinsurance.policy.Policy;

public record CarResponseDto(Long id,String brand, String model, int carValue, CarModel carmodel
        , int yearOfManufacture, double enginCapacity, int averageKmTraveledPerYear, Policy policy) {
}
