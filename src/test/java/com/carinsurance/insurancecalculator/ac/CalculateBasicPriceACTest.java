package com.carinsurance.insurancecalculator.ac;

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

class CalculateBasicPriceACTest {
    CalculateBasicPriceAC calculatePrice;
    @Mock
    PriceFormatter priceFormatter;
    @Mock
    CalculateDiscountAC discounts;
    @Mock
    Client client;
    @Mock
    Car car;
    @Mock
    CalculatePointsAC calculatePoints;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        calculatePrice = new CalculateBasicPriceAC(calculatePoints, discounts, priceFormatter);
        client = new Client();
        car = new Car();

    }

    @Test
    @DisplayName("Should return expected final AC insurance price ")
    void should_return_final_insurance_price() {
        // Given&When
        when(calculatePrice.calculateAcInsurancePrice(car, client)).thenReturn(2300.0);
        double result = calculatePrice.calculateAcInsurancePrice(car, client);
        // Then
        assertThat(2300.0).isEqualTo(result);
    }

    @Test
    @DisplayName("Should return expected AC insurance price with discount ")
    public void should_return_ac_insurance_price_with_discount() {
        // Given
        double basicPrice = 3000;
        given(discounts.calculateDiscountForAC(client)).willReturn(0.95);
        // When
        double result = basicPrice * discounts.calculateDiscountForAC(client);
        // Then
        assertThat(result).isEqualTo(2850.0);
    }

    @Test
    @DisplayName("Should return expected AC basic insurance price ")
    public void should_return_basic_ac_insurance_price() {
        // Given
        double expectedPrice = 4400.0;
        car.setCarValue(55000);
        given(calculatePoints.calculatePointsForAC(car, client)).willReturn(0.08);
        // When
        double result = calculatePoints.calculatePointsForAC(car, client) * car.getCarValue();
        // Then
        assertThat(result).isEqualTo(expectedPrice);
    }
}
