package com.carinsurance.insurancecalculator.oc;

import com.carinsurance.car.Car;
import com.carinsurance.client.Client;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.*;

import static com.carinsurance.insurancecalculator.FinalNumbers.MAX_OC_DISCOUNT;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import static org.mockito.Mockito.*;


class CalculateDiscountOCTest {

    private CalculateDiscountOC calculateDiscountOC;
    @Mock
    private Client client;
    @Mock
    private Car car;
    private Car secondCar;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        calculateDiscountOC = new CalculateDiscountOC();
    }

    @Test
    @DisplayName("Should return 0.95 when cars size = 2 ")
    void calculate_discount_with_two_cars() {
        // Given
        List<Car> cars = Arrays.asList(car, secondCar);
        // When
        when(client.getCars()).thenReturn(cars);
        double discount = calculateDiscountOC.calculateDiscountForOC(client);
        // Then
        assertThat(discount).isEqualTo(0.95);
    }

    @Test
    @DisplayName("Should return 1 when cars size = 1 ")
    void calculateDiscountForOC_withMultipleCars_returnsCorrectDiscount2() {
        // Given
        List<Car> cars = Arrays.asList(car);
        // When
        when(client.getCars()).thenReturn(cars);
        double discount = calculateDiscountOC.calculateDiscountForOC(client);
        // Then
        assertThat(discount).isEqualTo(1);
    }


    @Test
    @DisplayName("Should return MAX_OC_DISCOUNT when discount is equals MAX_OC_DISCOUNT ")
    public void should_Return_MaxDiscountWhen_equals_MaXDis() {
        // Given
        double discount = 0.6;
        double expectedDiscount = MAX_OC_DISCOUNT;

        // When
        double result = calculateDiscountOC.finalDiscountForOC(discount);

        // Then
        assertThat(result).isEqualTo(expectedDiscount);

    }

    @Test
    @DisplayName("Should return MAX_OC_DISCOUNT when discount is less than MAX_OC_DISCOUNT ")
    public void should_Return_MaxDiscount() {
        // Given
        double discount = 0.50;
        double expectedDiscount = MAX_OC_DISCOUNT;

        // When
        double result = calculateDiscountOC.finalDiscountForOC(discount);

        // Then
        assertThat(result).isEqualTo(expectedDiscount);

    }

    @Test
    @DisplayName("Should return 0.75 when discount is greater MAX_OC_DISCOUNT ")
    public void should_Return_Basic_Discount() {
        // Given
        double discount = 0.75;
        double expectedDiscount = 0.75;
        // When
        double result = calculateDiscountOC.finalDiscountForOC(discount);
        // Then
        assertThat(result).isEqualTo(expectedDiscount);

    }
}
