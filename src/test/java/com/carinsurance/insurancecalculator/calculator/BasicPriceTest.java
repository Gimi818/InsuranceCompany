package com.carinsurance.insurancecalculator.calculator;

import com.carinsurance.car.Car;
import com.carinsurance.client.Client;
import com.carinsurance.insurancecalculator.calculator.points.AcPoints;
import com.carinsurance.insurancecalculator.calculator.points.OcPoints;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class BasicPriceTest {
    @InjectMocks
    BasicPrice basicPrice;

    @Mock
    private AcPoints calculatorAC;
    @Mock
    private OcPoints calculatorOC;
    @Mock
    private Car car;
    @Mock
    private Client client;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

    }

    @Test
    @DisplayName("Should return 0.25 points")
    void calculate_points_for_AC_insurance() {


        double expectedPoints = 0.025;

        when(calculatorAC.pointsForClientAge(client)).thenReturn(0.005);
        when(calculatorAC.pointsForVehicleAge(car)).thenReturn(0.005);
        when(calculatorAC.pointsForEnginCapacity(car)).thenReturn(0.005);
        when(calculatorAC.pointsForAverageKMTraveledPerYear(car)).thenReturn(0.005);
        when(calculatorAC.pointsForTypeOfVehicle(car)).thenReturn(0.005);

        double actualPoints = basicPrice.calculatePointsForInsuranceType(car, client, calculatorAC);
        assertThat(actualPoints).isEqualTo(expectedPoints);
    }

    @Test
    @DisplayName("Should return 0.22 points")
    void calculate_points_for_OC_insurance() {

        double expectedPoints = 0.022;

        when(calculatorOC.pointsForClientAge(client)).thenReturn(0.005);
        when(calculatorOC.pointsForVehicleAge(car)).thenReturn(0.007);
        when(calculatorOC.pointsForEnginCapacity(car)).thenReturn(0.002);
        when(calculatorOC.pointsForAverageKMTraveledPerYear(car)).thenReturn(0.005);
        when(calculatorOC.pointsForTypeOfVehicle(car)).thenReturn(0.003);

        double actualPoints = basicPrice.calculatePointsForInsuranceType(car, client, calculatorOC);
        assertThat(actualPoints).isEqualTo(expectedPoints);

    }

    @Test
    @DisplayName("Should return 300 because basic insurance price is less than minimal price")
    void should_return_300() {
        // Given
        double insurancePrice = 50.0;
        double expectedPrice = 300;
        // When
        double finalPrice = basicPrice.minimalPrice(insurancePrice);

        // Then
        assertEquals(expectedPrice, finalPrice);
    }

    @Test
    @DisplayName("Should return expected price")
    void should_return_expected_price() {
        // Given
        double insurancePrice = 500;
        double expectedPrice = 500;
        // When
        double finalPrice = basicPrice.minimalPrice(insurancePrice);

        // Then
        assertEquals(expectedPrice, finalPrice);
    }

}



