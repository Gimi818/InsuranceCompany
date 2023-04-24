package com.carinsurance.car;

import jakarta.persistence.*;
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


}
