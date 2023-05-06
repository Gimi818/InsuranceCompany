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
@EqualsAndHashCode
@Builder
public class Policy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String policyName;

    private double  priceOfInsurance;
    private LocalDate startDate;

    private LocalDate endDate;



}
