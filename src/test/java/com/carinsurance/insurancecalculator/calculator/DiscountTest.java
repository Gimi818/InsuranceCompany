package com.carinsurance.insurancecalculator.calculator;

import com.carinsurance.car.Car;
import com.carinsurance.client.Client;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.*;
import java.util.Arrays;

import static com.carinsurance.insurancecalculator.FinalNumbers.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class DiscountTest {
    @InjectMocks
    private Discount calculateDiscount;
    @Mock
    private Client client;
    @Mock
    private Car car;
    @Mock
    private Car secondCar;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

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
        double discount = calculateDiscount.calculateDiscountForInsuranceType(client, 0.02);
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
        double discount = calculateDiscount.calculateDiscountForInsuranceType(client, AC_DISCOUNT);
        // Then
        assertThat(discount).isEqualTo(1);
    }


    @Test
    @DisplayName("Should return MAX_AC_DISCOUNT when discount is equals MAX_AC_DISCOUNT ")
    void should_Return_Max_Discount() {
        // Given
        double discount = 0.75;
        double expectedDiscount = MAX_AC_DISCOUNT;

        // When
        double result = calculateDiscount.finalDiscountForAC(discount);

        // Then
        assertEquals(expectedDiscount, result);
    }

    @Test
    @DisplayName("Should return MAX_AC_DISCOUNT when discount is less than MAX_AC_DISCOUNT ")
    void should_Return_MaxDiscount() {
        // Given
        double discount = 0.50;

        // When
        double result = calculateDiscount.finalDiscountForAC(discount);

        // Then
        assertEquals(MAX_AC_DISCOUNT, result);
    }

    @Test
    @DisplayName("Should return 0.8 when discount is greater MAX_AC_DISCOUNT ")
    void should_return_basic_ac_discount() {
        // Given
        double discount = 0.8;
        double expectedDiscount = 0.8;
        ;
        // When
        double result = calculateDiscount.finalDiscountForAC(discount);
        // Then
        assertEquals(expectedDiscount, result);
    }

    @Test
    @DisplayName("Should return 0.95 when cars size = 2 ")
    void calculate_oc_discount_with_two_cars() {
        // Given
        List<Car> cars = Arrays.asList(car, secondCar);
        // When
        when(client.getCars()).thenReturn(cars);
        double discount = calculateDiscount.calculateDiscountForInsuranceType(client, OC_DISCOUNT);
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
        double discount = calculateDiscount.calculateDiscountForInsuranceType(client, OC_DISCOUNT);
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
        double result = calculateDiscount.finalDiscountForOC(discount);

        // Then
        assertThat(result).isEqualTo(expectedDiscount);

    }

    @Test
    @DisplayName("Should return MAX_OC_DISCOUNT when discount is less than MAX_OC_DISCOUNT ")
    public void should_Return_AC_MaxDiscount() {
        // Given
        double discount = 0.50;
        double expectedDiscount = MAX_OC_DISCOUNT;

        // When
        double result = calculateDiscount.finalDiscountForOC(discount);

        // Then
        assertThat(result).isEqualTo(expectedDiscount);

    }

    @Test
    @DisplayName("Should return 0.75 when discount is greater MAX_OC_DISCOUNT ")
    public void should_return_basic_oc_discount() {
        // Given
        double discount = 0.75;
        double expectedDiscount = 0.75;
        // When
        double result = calculateDiscount.finalDiscountForOC(discount);
        // Then
        assertThat(result).isEqualTo(expectedDiscount);

    }

    @Test
    @DisplayName("Should return 0.75 when discount is greater MAX_OC_DISCOUNT ")
    void should_return_basic_oc_discoun2t() {
        // Given
        double discount = 0.75;
        double expectedPrice = 2250;
        double basicPrice = 3000.0;
        // When

        double finalPrice = calculateDiscount.priceWithDiscountOc(basicPrice * discount, client);
        // Then
        assertThat(finalPrice).isEqualTo(expectedPrice);

    }
    @Test
    @DisplayName("Should return 0.75 when discount is greater MAX_OC_DISCOUNT ")
    void should_return_basic_oc_discoun2t2() {
        // Given
        double discount = 0.75;
        double expectedPrice = 2250;
        double basicPrice = 3000.0;
        // When

        double finalPrice = calculateDiscount.priceWithDiscountAc(basicPrice * discount, client);
        // Then
        assertThat(finalPrice).isEqualTo(expectedPrice);

    }

}

