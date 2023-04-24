package com.carinsurance.car;

import com.carinsurance.car.dto.CarRequestDto;
import com.carinsurance.car.dto.CarResponseDto;
import com.carinsurance.car.exception.CarNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import static com.carinsurance.car.CarMapper.carMapper;

@Service
@AllArgsConstructor
public class CarService {

    private final CarRepository carRepository;

    public Car saveCar(CarRequestDto carRequestDto) {

        Car newCar = carRepository.save(carMapper.dtoToEntity(carRequestDto));

        return newCar;
    }


    public CarResponseDto findCarById(Long id) {

        Car car = carRepository.findById(id).orElseThrow(() -> new CarNotFoundException(id));
        return carMapper.entityToDto(car);
    }

}
