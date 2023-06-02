package com.carinsurance.car.dto;

import com.carinsurance.car.CarModel;
import com.carinsurance.car.ParkingType;
import com.carinsurance.policy.Policy;

public record CarResponseDto(Long id, String brand, String model, int carValue, CarModel carModel,
       ParkingType parkingType, int yearOfManufacture, double enginCapacity, int averageKmTraveledPerYear, Policy policy) {
}
