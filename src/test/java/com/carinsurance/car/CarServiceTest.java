package com.carinsurance.car;

import com.carinsurance.car.dto.CarRequestDto;
import com.carinsurance.car.dto.CreatedCarDto;
import com.carinsurance.car.enums.CarModel;
import com.carinsurance.car.enums.ParkingType;

import com.carinsurance.client.Client;
import com.carinsurance.client.ClientFacade;

import com.carinsurance.common.exception.exceptions.NotFoundException;
import com.carinsurance.policy.PolicyRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.*;


@ExtendWith(MockitoExtension.class)
class CarServiceTest {
    @Mock
    private CarRepository carRepository;
    @Mock
    private CarMapper carMapper;
    @Mock
    private PolicyRepository policyRepository;

    @InjectMocks
    private CarService carService;
    @Mock
    private CarRequestDto carRequestDto;
    @Mock
    private CreatedCarDto createdCarDto;
    @Mock
    private Car car;
    @Mock
    private CarFacade carFacade;
    @Mock
    private ClientFacade clientFacade;

    @BeforeEach
    void setUp() {
        carRequestDto = new CarRequestDto("Bmw", "X5", 30000, null, null, 2010, 3.0, 21000);
        car = new Car("Bmw", "X5", 40000, CarModel.CAR, ParkingType.GARAGE, 2010, 2.3, 12000, null);
    }

    @Test
    @DisplayName("Should save car")
    void should_save_car() {

        Long clientId = 1L;
        Client client = new Client();
        CreatedCarDto createdCarDto = new CreatedCarDto("BMW", "X5", 2010);


        when(clientFacade.findById(clientId)).thenReturn(client);
        when(carMapper.dtoToEntity(carRequestDto)).thenReturn(car);
        when(carRepository.save(any())).thenReturn(car);
        when(carMapper.entityToDto(car)).thenReturn(createdCarDto);


        CreatedCarDto result = carService.saveCar(carRequestDto, clientId);

        verify(clientFacade).findById(clientId);
        verify(carRepository).save(car);
        verify(clientFacade).saveClientWithAddedCar(client);
        verify(carMapper).entityToDto(car);
    }


    @Test
    @DisplayName("Should find car by ID")
    void should_find_car_by_id() {
        given(carRepository.findById(1L)).willReturn(Optional.of(car));
        assertThat(carService.findCarById(1L)).isEqualTo(car);
    }

    @Test
    void testSaveCarWithAddedInsurance() {
        when(carRepository.save(car)).thenReturn(car);

        Car savedCar = carService.saveCarWithAddedInsurance(car);

        assertEquals(car, savedCar);
        verify(carRepository).save(car);
    }

    @Test
    @DisplayName("Should throw NotFoundException when car is not found")
    void should_throw_CarNotFoundException() {
        // Given
        Long carId = 1L;
        //when
        when(carRepository.findById(carId)).thenReturn(Optional.empty());
        //Then
        assertThrows(NotFoundException.class, () -> carService.findCarById(carId));

    }

    @Test
    @DisplayName("Should throw NOT_INSURED exception ")
    void should_throw_NOT_INSURED_exception() {
        Client client = new Client();
        client.getCars().add(car);


        NotFoundException exception = assertThrows(NotFoundException.class,
                () -> carService.checkCarsInsuranceStatus(client));

        assertEquals("You cannot add another car because the previous one is not insured", exception.getMessage());
    }

    @Test
    @DisplayName("Should find car without policy for client")
    void should_find_car_without_policy() {

        Long clientId = 1L;
        Client client = new Client();

        client.getCars().add(car);


        when(clientFacade.findById(clientId)).thenReturn(client);

        Car result = carService.findCarWithoutPolicyForClient(clientId);

        assertEquals(car, result);


        verify(clientFacade).findById(clientId);
    }

}
