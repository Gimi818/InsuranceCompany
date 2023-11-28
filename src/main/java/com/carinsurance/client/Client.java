package com.carinsurance.client;

import com.carinsurance.car.Car;

import javax.persistence.*;

import com.carinsurance.common.entity.AbstractEntity;
import lombok.*;

import java.util.*;

@Entity
@Table(name = "client")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Client  extends AbstractEntity {

    private String firstname;
    private String lastname;
    private int age;

    @OneToMany(cascade = CascadeType.ALL,
            orphanRemoval = true)
    @JoinColumn(name = "client_id")
    private List<Car> cars = new ArrayList<>();
}
