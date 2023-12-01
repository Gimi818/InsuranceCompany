package com.carinsurance.policy.dto;


import com.carinsurance.policy.PolicyType;

import java.time.LocalDate;

public record PolicyResponseDto(double priceOfInsurance, PolicyType policyType, LocalDate startDate, LocalDate endDate) {
}
