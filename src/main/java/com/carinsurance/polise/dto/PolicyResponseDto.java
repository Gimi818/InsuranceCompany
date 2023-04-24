package com.carinsurance.polise.dto;

import com.carinsurance.car.Car;

import java.time.LocalDate;

public record PolicyResponseDto(Long id, double priceOfInsurance, LocalDate startDate, LocalDate endDate, Car car) {
}
