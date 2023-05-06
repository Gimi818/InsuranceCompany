package com.carinsurance.client.dto;

import com.carinsurance.car.Car;

import java.util.List;

public record ClientRequestDto(String firstname, String lastname, int age, List<Car> cars) {


}
