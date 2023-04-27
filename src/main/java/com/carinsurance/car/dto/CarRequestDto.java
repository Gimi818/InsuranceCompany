package com.carinsurance.car.dto;

import com.carinsurance.car.CarModel;

import javax.validation.constraints.NotBlank;


public record CarRequestDto(String brand, String model, @NotBlank int carValue, @NotBlank CarModel carmodel
        , @NotBlank int yearOfManufacture, @NotBlank double enginCapacity, @NotBlank int averageKmTraveledPerYear) {
}
