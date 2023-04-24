package com.carinsurance.polise;

import com.carinsurance.car.Car;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "policy")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Policy {

    @Id
    @GeneratedValue
    private Long id;

    private double  priceOfInsurance;
    private LocalDate startDate;

    private LocalDate endDate;

    @OneToOne(mappedBy = "policy")
    private Car car;


}
