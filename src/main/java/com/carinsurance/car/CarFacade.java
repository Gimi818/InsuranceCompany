package com.carinsurance.car;

import com.carinsurance.car.dto.CarRequestDto;
import com.carinsurance.car.dto.CreatedCarDto;

public interface CarFacade {

    Car saveCarWithAddedInsurance(Car car);

    CreatedCarDto saveCar(CarRequestDto carRequestDto, Long clientId);

    Car findCarWithoutPolicyForClient(Long clientId);

    Car findCarById(Long id);
}
