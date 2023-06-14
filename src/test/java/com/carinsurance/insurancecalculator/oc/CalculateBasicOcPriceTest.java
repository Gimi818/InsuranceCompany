package com.carinsurance.insurancecalculator.oc;

import com.carinsurance.car.Car;
import com.carinsurance.car.CarRepository;
import com.carinsurance.client.Client;
import com.carinsurance.client.ClientRepository;
import com.carinsurance.insurancecalculator.PriceFormatter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

class CalculateBasicOcPriceTest {
    @Mock
    CalculateBasicOcPrice calculateBasicOcPrice;
    @Mock
    CalculateDiscountOC discounts;
    @Mock
    Client client;
    @Mock
    Car car;
    @Mock
    CalculatePointsOC calculatePoints;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        calculateBasicOcPrice = new CalculateBasicOcPrice(calculatePoints, discounts);
        client = new Client();
        car = new Car();
    }

    @Test
    @DisplayName("Should return expected OC insurance price with discount ")
    public void should_return_insurance_oc_price_with_discount() {
        // Given
        double basicPrice = 1300;
        given(discounts.calculateDiscountForOC(client)).willReturn(0.9);
        // When
        double result = basicPrice * discounts.calculateDiscountForOC(client);
        // Then
        assertThat(result).isEqualTo(1170.0);
    }

    @Test
    @DisplayName("Should return expected basic insurance price ")
    public void should_return_basic_insurance_price() {
        // Given
        car.setCarValue(40000);
        given(calculatePoints.calculatePointsForOC(car, client)).willReturn(0.05);
        // When
        double result = calculatePoints.calculatePointsForOC(car, client) * car.getCarValue();
        // Then
        assertThat(result).isEqualTo(2000.0);
    }

    @Test
    @DisplayName("Should return 300 when insurance price is less 300")
    public void should_return_300() {
        // Given
        double price = 250.0;
        // When
        double result = calculateBasicOcPrice.minimalPrice(price);
        // Then
        assertThat(result).isEqualTo(300.0);
    }

    @Test
    @DisplayName("Should return 700 when insurance price is 700")
    public void should_return_700() {
        // Given
        double price = 700.0;
        // When
        double result = calculateBasicOcPrice.minimalPrice(price);
        // Then
        assertThat(result).isEqualTo(700.0);
    }

    @Test
    @DisplayName("Should return 300 when insurance price is equals 300")
    public void should_return_the_same_price() {
        // Given
        double price = 300.0;
        // When
        double result = calculateBasicOcPrice.minimalPrice(price);
        // Then
        assertThat(result).isEqualTo(300.0);
    }

}
