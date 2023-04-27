package com.carinsurance.car;

import com.carinsurance.car.dto.CarRequestDto;
import com.carinsurance.car.dto.CarResponseDto;
import com.carinsurance.car.exception.CarNotFoundException;
import com.carinsurance.policy.Policy;
import com.carinsurance.policy.PolicyRepository;
import com.carinsurance.policy.exception.PolicyNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class CarService {

    private final CarRepository carRepository;
    private  final CarMapper carMapper;

    private final PolicyRepository policyRepository;

    public Car saveCar(CarRequestDto carRequestDto) {

        Car newCar = carRepository.save(carMapper.dtoToEntity(carRequestDto));

        return newCar;
    }


    public CarResponseDto findCarById(Long id) {

        Car car = carRepository.findById(id).orElseThrow(() -> new CarNotFoundException(id));
        return carMapper.entityToDto(car);
    }


    public Car assignPolicyToCar(Long carId, Long policyId) {
        Car car = carRepository.findById(carId).orElseThrow(() -> new CarNotFoundException(carId));
        Policy policy = policyRepository.findById(policyId).orElseThrow(() -> new PolicyNotFoundException(policyId));
        car.setPolicy(policy);

        return carRepository.save(car);
    }

}

