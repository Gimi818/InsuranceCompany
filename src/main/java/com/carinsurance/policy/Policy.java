package com.carinsurance.policy;

import javax.persistence.*;

import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "policy")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Policy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String policyName;
    private String insuranceType;

    private double priceOfInsurance;
    private LocalDate startDate;

    private LocalDate endDate;


}
