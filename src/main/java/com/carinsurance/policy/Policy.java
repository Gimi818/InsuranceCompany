package com.carinsurance.policy;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "policy")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Policy {

    @Id
    @GeneratedValue
    private Long id;
    private String policyName;

    private double  priceOfInsurance;
    private LocalDate startDate;

    private LocalDate endDate;



}
