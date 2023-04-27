package com.carinsurance.client.dto;

import com.carinsurance.car.Car;


import javax.validation.constraints.NotBlank;
import java.util.List;

public record ClientRequestDto(String firstname, String lastname, @NotBlank int age, List<Car> cars) {
}
