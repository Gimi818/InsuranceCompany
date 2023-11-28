package com.carinsurance.car;

import com.carinsurance.car.dto.CarRequestDto;
import com.carinsurance.car.dto.CarResponseDto;

public interface CarFacade {

    Car saveCarWithAddedInsurance(Car car);

    Car saveCar(CarRequestDto carRequestDto, Long clientId);

    Car findCarWithoutPolicyForClient(Long clientId);

    Car findCarById(Long id);
}
