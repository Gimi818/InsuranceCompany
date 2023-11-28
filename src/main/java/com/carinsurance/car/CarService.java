package com.carinsurance.car;

import com.carinsurance.car.dto.CarRequestDto;
import com.carinsurance.car.dto.CarResponseDto;

import com.carinsurance.client.Client;
import com.carinsurance.client.ClientFacade;

import com.carinsurance.common.exception.exceptions.NotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@AllArgsConstructor
@Log4j2
public class CarService implements CarFacade {
    private final ClientFacade clientFacade;
    private final CarRepository carRepository;
    private final CarMapper carMapper;


    public Car saveCar(CarRequestDto carRequestDto, Long clientId) {
        Car newCar = carRepository.save(carMapper.dtoToEntity(carRequestDto));
        log.info("Saved car {}", newCar);
        Client client = clientFacade.findById(clientId);
        client.getCars().add(newCar);
        clientFacade.saveClientWithAddedCar(client);
        return newCar;
    }

    public Car saveCarWithAddedInsurance(Car car) {
        carRepository.save(car);
        return car;
    }

    public Car findCarWithoutPolicyForClient(Long clientId) {
        Client client = clientFacade.findById(clientId);
        List<Car> cars = client.getCars();
        for (Car car : cars) {
            if (car.getPolicy() == null) {
                return car;
            }
            throw new NotFoundException("Car has policy");
        }
        throw new NotFoundException(clientId);
    }

    public Car findCarById(Long id) {
        Car car = carRepository.findById(id).orElseThrow(() -> new NotFoundException(id));
        log.info("Found car {}", car);

        return car;
    }


}

