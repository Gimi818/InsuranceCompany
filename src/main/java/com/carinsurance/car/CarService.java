package com.carinsurance.car;

import com.carinsurance.car.dto.CarRequestDto;

import com.carinsurance.car.dto.CreatedCarDto;
import com.carinsurance.client.Client;
import com.carinsurance.client.ClientFacade;

import com.carinsurance.common.exception.exceptions.NotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import static com.carinsurance.car.CarService.ErrorMessages.*;

import java.util.List;


@Service
@AllArgsConstructor
@Log4j2
public class CarService implements CarFacade {
    private final ClientFacade clientFacade;
    private final CarRepository carRepository;
    private final CarMapper carMapper;


    public CreatedCarDto saveCar(CarRequestDto carRequestDto, Long clientId) {
        Client client = clientFacade.findById(clientId);
        checkCarsInsuranceStatus(client);

        Car newCar = carRepository.save(carMapper.dtoToEntity(carRequestDto));
        log.info("Saved car {}", newCar);
        client.getCars().add(newCar);
        clientFacade.saveClientWithAddedCar(client);
        return carMapper.entityToDto(newCar);
    }

    private void checkCarsInsuranceStatus(Client client) {
        List<Car> cars = client.getCars();

        for (Car car : cars) {
            if (car.getPolicy() == null) {
                throw new NotFoundException(NOT_INSURED, client.getId());
            }
        }
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
        }

        throw new NotFoundException(CARS_HAVE_POLICIES, clientId);
    }

    public Car findCarById(Long id) {
        Car car = carRepository.findById(id).orElseThrow(() -> new NotFoundException(CAR_NOT_FOUND, id));
        log.info("Found car with ID {}", id);
        return car;
    }

    static final class ErrorMessages {
        static final String CARS_HAVE_POLICIES = "All cars have policies for client with ID: %d";
        static final String NOT_INSURED = "You cannot add another car because the previous one is not insured";
        static final String CAR_NOT_FOUND = "Car with ID %d not found";
    }
}

