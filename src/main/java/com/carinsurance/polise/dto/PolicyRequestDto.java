package com.carinsurance.polise.dto;

import com.carinsurance.car.Car;

import java.time.LocalDate;

public record PolicyRequestDto(double  priceOfInsurance, LocalDate startDate ,LocalDate endDate, Car car) {
}
