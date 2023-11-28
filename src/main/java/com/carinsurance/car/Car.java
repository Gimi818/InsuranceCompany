package com.carinsurance.car;

import com.carinsurance.car.enums.CarModel;
import com.carinsurance.car.enums.ParkingType;
import com.carinsurance.common.entity.AbstractEntity;
import com.carinsurance.policy.Policy;

import javax.persistence.*;

import lombok.*;

@Entity
@Table(name = "car")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class Car extends AbstractEntity {

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
