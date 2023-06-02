package com.carinsurance.policy.dto;


import java.time.LocalDate;

public record PolicyRequestDto(double priceOfInsurance, String insuranceType, LocalDate startDate, LocalDate endDate) {
}
