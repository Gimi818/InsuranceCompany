package com.carinsurance.car;

import com.carinsurance.car.dto.CarRequestDto;
import com.carinsurance.car.dto.CarResponseDto;
import com.carinsurance.car.exception.CarNotFoundException;
import com.carinsurance.policy.Policy;
import com.carinsurance.policy.PolicyRepository;
import com.carinsurance.policy.exception.PolicyNotFoundException;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
@Log4j2
public class CarService {

    private final CarRepository carRepository;
    private final CarMapper carMapper;
    private final PolicyRepository policyRepository;


    public Car saveCar(CarRequestDto carRequestDto) {
        log.info("Saving car {}", carRequestDto);
        Car newCar = carRepository.save(carMapper.dtoToEntity(carRequestDto));

        log.info("Saved car {}", newCar);
        return newCar;
    }


    public CarResponseDto findCarById(Long id) {
        log.info("Finding car with ID {}", id);

        Car car = carRepository.findById(id).orElseThrow(() -> new CarNotFoundException(id));
        log.info("Found car {}", car);

        return carMapper.entityToDto(car);
    }


    public Car assignPolicyToCar(Long carId, Long policyId) {
        log.info("Assigning policy with ID {} to car with ID {}", policyId, carId);

        Car car = carRepository.findById(carId).orElseThrow(() -> new CarNotFoundException(carId));
        Policy policy = policyRepository.findById(policyId).orElseThrow(() -> new PolicyNotFoundException(policyId));
        car.setPolicy(policy);

        log.info("Assigned policy with ID {} to car with ID {}", policyId, carId);
        return carRepository.save(car);
    }

}

