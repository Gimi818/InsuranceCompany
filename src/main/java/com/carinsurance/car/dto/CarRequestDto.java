package com.carinsurance.car.dto;

import com.carinsurance.car.CarModel;
import com.carinsurance.car.ParkingType;


public record CarRequestDto(String brand, String model, int carValue, CarModel carModel, ParkingType parkingType,
         int yearOfManufacture, double enginCapacity, int averageKmTraveledPerYear) {
}
