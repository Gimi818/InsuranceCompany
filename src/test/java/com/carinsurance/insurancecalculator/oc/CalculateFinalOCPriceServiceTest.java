package com.carinsurance.insurancecalculator.oc;

import com.carinsurance.car.Car;
import com.carinsurance.car.CarRepository;
import com.carinsurance.car.exception.CarNotFoundException;
import com.carinsurance.client.Client;
import com.carinsurance.client.ClientRepository;
import com.carinsurance.client.exception.ClientNotFoundException;
import com.carinsurance.insurancecalculator.PriceFormatter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertThrows;

import static org.mockito.Mockito.when;

class CalculateFinalOCPriceServiceTest {
    @Mock
    CalculateFinalOCPriceService calculateFinalPrice;
    @Mock
    CalculateBasicOcPrice calculateBasicOcPrice;
    @Mock
    PriceFormatter priceFormatter;
    @Mock
    CarRepository carRepository;
    @Mock
    ClientRepository clientRepository;
    @Mock
    Client client;
    @Mock
    Car car;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        calculateFinalPrice = new CalculateFinalOCPriceService(priceFormatter, carRepository, clientRepository, calculateBasicOcPrice);
    }

    @Test
    @DisplayName("Should return expected final OC insurance price ")
    void should_return_final_insurance_price() {
        // Given&When
        when(carRepository.findById(1L)).thenReturn(Optional.of(car));
        when(clientRepository.findById(1L)).thenReturn(Optional.of(client));
        when(calculateFinalPrice.calculateOcInsurancePrice(1L, 1L)).thenReturn(950.0);

        double result = calculateFinalPrice.calculateOcInsurancePrice(1L, 1L);
        // Then
        assertThat(result).isEqualTo(950.0);
    }

    @Test
    @DisplayName("Should throw CarNotFoundException when car is not found")
    void should_throw_CarNotFoundException() {
        // Given
        Long carId = 1L;
        // When
        when(carRepository.findById(carId)).thenReturn(Optional.empty());
        when(clientRepository.findById(1L)).thenReturn(Optional.of(client));
        // Then
        assertThrows(CarNotFoundException.class, () -> calculateFinalPrice.calculateOcInsurancePrice(1L, carId));
    }

    @Test
    @DisplayName("Should throw ClientNotFoundException when client is not found")
    void should_throw_ClientNotFoundException() {
        // Given
        Long clientId = 1L;
        //when
        when(clientRepository.findById(clientId)).thenReturn(Optional.empty());
        when(carRepository.findById(1L)).thenReturn(Optional.of(car));
        //Then
        assertThrows(ClientNotFoundException.class, () -> calculateFinalPrice.calculateOcInsurancePrice(1L, clientId));
    }


}
