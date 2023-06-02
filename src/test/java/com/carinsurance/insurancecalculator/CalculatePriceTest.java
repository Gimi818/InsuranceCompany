package com.carinsurance.insurancecalculator;

import com.carinsurance.car.Car;

import com.carinsurance.client.Client;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.assertj.core.api.Assertions.assertThat;

import static org.mockito.Mockito.when;

class CalculatePriceTest {
    CalculatePrice calculatePrice;
    @Mock
    Client client;

    @Mock
    Car car;
    Car secondCar;


    @Mock
    CalculatePoints calculatePoints;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        calculatePrice = new CalculatePrice(calculatePoints);

        car = new Car(1L, "BMW", "X5", 62000, null, 0, 0, 0, null);

        secondCar = new Car(2L, "Audi", "A5", 45000, null, 0, 0, 0, null);
    }

    @Test
    @DisplayName("Should return insurance price 1426.0 zł for second car ")
    void calculate_final_price_for_first_car() {
        //given & when
        when(calculatePoints.calculatePointsForOC(car, client)).thenReturn(0.023);
        double price = calculatePrice.calculateFinalPriceForOC(car, client);
        //then
        assertThat(price).isEqualTo(1426.0);

    }

    @Test
    @DisplayName("Should return insurance price 855.0 zł for car ")
    void calculate_final_price_for_second_car() {

        //given & when
        when(calculatePoints.calculatePointsForOC(secondCar, client)).thenReturn(0.019);
        double price = calculatePrice.calculateFinalPriceForOC(secondCar, client);
        //then
        assertThat(price).isEqualTo(855.0);
    }


    @Test
    @DisplayName("Should return insurance price 300 zł  when price is less than 300 ")
    void calculate_minimal_price() {

        //given & when
        when(calculatePoints.calculatePointsForOC(secondCar, client)).thenReturn(0.0001);
        double price = calculatePrice.calculateFinalPriceForOC(secondCar, client);
        //then
        assertThat(price).isEqualTo(300);
    }


}
