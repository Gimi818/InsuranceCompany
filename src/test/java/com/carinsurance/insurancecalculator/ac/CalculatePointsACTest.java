package com.carinsurance.insurancecalculator.ac;

import com.carinsurance.car.Car;
import com.carinsurance.client.Client;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class CalculatePointsACTest {

    private CalculatePointsAC calculatePoints;

    @Mock
    private CalculatorAC calculatorAC;
    @Mock
    Car car;
    @Mock
    Client client;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        calculatePoints = new CalculatePointsAC(calculatorAC);
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

        double actualPoints = calculatePoints.calculatePointsForAC(car, client);

        assertThat(actualPoints).isEqualTo(expectedPoints);
    }

    @Test
    @DisplayName("Should return 0.22 points")
    void calculate_points_for_AC_insurance_second() {

        double expectedPoints = 0.022;

        when(calculatorAC.pointsForClientAge(client)).thenReturn(0.005);
        when(calculatorAC.pointsForVehicleAge(car)).thenReturn(0.007);
        when(calculatorAC.pointsForEnginCapacity(car)).thenReturn(0.002);
        when(calculatorAC.pointsForAverageKMTraveledPerYear(car)).thenReturn(0.005);
        when(calculatorAC.pointsForTypeOfVehicle(car)).thenReturn(0.003);

        double actualPoints = calculatePoints.calculatePointsForAC(car, client);
        assertThat(actualPoints).isEqualTo(expectedPoints);

    }
}
