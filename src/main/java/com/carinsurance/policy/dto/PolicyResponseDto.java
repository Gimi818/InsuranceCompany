package com.carinsurance.policy.dto;


import java.time.LocalDate;

public record PolicyResponseDto(Long id, double priceOfInsurance, LocalDate startDate, LocalDate endDate) {
}
