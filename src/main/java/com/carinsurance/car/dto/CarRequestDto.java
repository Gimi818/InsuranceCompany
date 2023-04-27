package com.carinsurance.car.dto;

import com.carinsurance.car.CarModel;


public record CarRequestDto(String brand, String model, int carValue, CarModel carmodel
        , int yearOfManufacture, double enginCapacity, int averageKmTraveledPerYear) {
}
