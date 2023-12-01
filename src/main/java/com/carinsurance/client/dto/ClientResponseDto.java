package com.carinsurance.client.dto;

import com.carinsurance.car.Car;

import java.util.*;

public record ClientResponseDto(String firstname, String lastname, int age, List<Car> cars) {
}
