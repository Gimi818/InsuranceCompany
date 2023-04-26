package com.carinsurance.car;

import com.carinsurance.client.Client;
import com.carinsurance.policy.Policy;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "car")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String brand;
    private String model;
    private int carValue;
    @Enumerated(EnumType.STRING)
    private CarModel carmodel;
    private int yearOfManufacture;
    private double enginCapacity;
    private int averageKmTraveledPerYear;


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "policy_id")
    private Policy policy;

}
