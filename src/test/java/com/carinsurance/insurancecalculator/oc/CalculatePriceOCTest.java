package com.carinsurance.insurancecalculator.oc;

import com.carinsurance.car.Car;
import com.carinsurance.client.Client;
import com.carinsurance.insurancecalculator.PriceFormatter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

class CalculatePriceOCTest {
    @Mock
    CalculatePriceOC calculatePrice;
    @Mock
    PriceFormatter priceFormatter;
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
        calculatePrice = new CalculatePriceOC(calculatePoints, discounts, priceFormatter);
        client = new Client();
        car = new Car();
    }

    @Test
    @DisplayName("Should return expected final OC insurance price ")
    void should_return_final_insurance_price() {
        // Given&When
        when(calculatePrice.calculateOcInsurancePrice(car, client)).thenReturn(950.0);
        double result = calculatePrice.calculateOcInsurancePrice(car, client);
        // Then
        assertThat(950.0).isEqualTo(result);
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
        assertThat(1170.0).isEqualTo(result);
    }

    @Test
    @DisplayName("Should return expected basic insurance price ")
    public void should_return_basic_insurance_price() {
        // Given
        double expectedPrice = 2000.0;
        car.setCarValue(40000);
        given(calculatePoints.calculatePointsForOC(car, client)).willReturn(0.05);
        // When
        double result = calculatePoints.calculatePointsForOC(car, client) * car.getCarValue();
        // Then
        assertThat(expectedPrice).isEqualTo(result);
    }

    @Test
    @DisplayName("Should return 300 when insurance price is less 300")
    public void should_return_300() {
        // Given
        double price = 250.0;
        double expectedPrice = 300.0;
        // When
        double result = calculatePrice.minimalPrice(price);
        // Then
        assertThat(expectedPrice).isEqualTo(result);
    }

    @Test
    @DisplayName("Should return 700 when insurance price is 700")
    public void should_return_700() {
        // Given
        double price = 700.0;
        // When
        double result = calculatePrice.minimalPrice(price);
        // Then
        assertThat(700.0).isEqualTo(result);
    }

    @Test
    @DisplayName("Should return 300 when insurance price is equals 300")
    public void should_return_the_same_price() {
        // Given
        double price = 300.0;
        // When
        double result = calculatePrice.minimalPrice(price);
        // Then
        assertThat(300.0).isEqualTo(result);
    }


}
