package com.carinsurance.policy.dto;


import java.time.LocalDate;

public record PolicyRequestDto(double  priceOfInsurance, LocalDate startDate ,LocalDate endDate) {
}
