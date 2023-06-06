package com.carinsurance.car;

import com.carinsurance.policy.Policy;

import javax.persistence.*;

import lombok.*;

@Entity
@Table(name = "car")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String brand;
    private String model;
    private int carValue;
    @Enumerated(EnumType.STRING)
    private CarModel carModel;
    @Enumerated(EnumType.STRING)
    private ParkingType parkingType;
    private int yearOfManufacture;
    private double enginCapacity;
    private int averageKmTraveledPerYear;


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "policy_id")
    private Policy policy;

}
