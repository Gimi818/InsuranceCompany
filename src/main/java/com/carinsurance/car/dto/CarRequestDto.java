package com.carinsurance.car.dto;

import com.carinsurance.car.enums.CarModel;
import com.carinsurance.car.enums.ParkingType;


public record CarRequestDto(String brand, String model, int carValue, CarModel carModel, ParkingType parkingType,
         int yearOfManufacture, double enginCapacity, int averageKmTraveledPerYear) {
}
