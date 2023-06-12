package com.carinsurance.insurancecalculator.ac;

import com.carinsurance.car.Car;
import com.carinsurance.client.Client;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static com.carinsurance.insurancecalculator.FinalNumbers.MAX_AC_DISCOUNT;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class CalculateDiscountACTest {

    private CalculateDiscountAC calculateDiscountAC;
    @Mock
    private Client client;
    @Mock
    private Car car;
    @Mock
    private Car secondCar;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        calculateDiscountAC = new CalculateDiscountAC();
        car = new Car();
        secondCar = new Car();
    }

    @Test
    @DisplayName("Should return 0.98 when cars size = 2 ")
    void calculate_discount_with_two_cars() {
        // Given
        List<Car> cars = Arrays.asList(car, secondCar);
        // When
        when(client.getCars()).thenReturn(cars);
        double discount = calculateDiscountAC.calculateDiscountForAC(client);
        // Then
        assertThat(discount).isEqualTo(0.98);
    }

    @Test
    @DisplayName("Should return 1 when cars size = 1 ")
    void should_return_1() {
        // Given
        List<Car> cars = Arrays.asList(car);
        // When
        when(client.getCars()).thenReturn(cars);
        double discount = calculateDiscountAC.calculateDiscountForAC(client);
        // Then
        assertThat(discount).isEqualTo(1);
    }


    @Test
    @DisplayName("Should return MAX_AC_DISCOUNT when discount is equals MAX_AC_DISCOUNT ")
    public void should_Return_Max_Discount() {
        // Given
        double discount = 0.75;
        double expectedDiscount = MAX_AC_DISCOUNT;

        // When
        double result = calculateDiscountAC.finalDiscountForAC(discount);

        // Then
        assertEquals(expectedDiscount, result);
    }

    @Test
    @DisplayName("Should return MAX_AC_DISCOUNT when discount is less than MAX_AC_DISCOUNT ")
    public void should_Return_MaxDiscount() {
        // Given
        double discount = 0.50;
        double expectedDiscount = MAX_AC_DISCOUNT;

        // When
        double result = calculateDiscountAC.finalDiscountForAC(discount);

        // Then
        assertEquals(expectedDiscount, result);
    }

    @Test
    @DisplayName("Should return 0.8 when discount is greater MAX_AC_DISCOUNT ")
    public void should_Return_Basic_Discount() {
        // Given
        double discount = 0.8;
        double expectedDiscount = 0.8;
        ;
        // When
        double result = calculateDiscountAC.finalDiscountForAC(discount);
        // Then
        assertEquals(expectedDiscount, result);
    }
}


