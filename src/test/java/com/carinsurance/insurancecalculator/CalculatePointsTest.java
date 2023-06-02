package com.carinsurance.insurancecalculator;

import com.carinsurance.car.Car;
import com.carinsurance.client.Client;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class CalculatePointsTest {


    private CalculatePoints calculatePoints;

    @Mock
    private CalculatorOC calculator;
    @Mock
    Car car;
    @Mock
    Client client;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        calculatePoints = new CalculatePoints(calculator);

    }

    @Test
    @DisplayName("Should return 0.25 points")
    void calculate_points() {

        double expectedPoints = 0.025;

        when(calculator.pointsForClientAge(client)).thenReturn(0.005);
        when(calculator.pointsForVehicleAge(car)).thenReturn(0.005);
        when(calculator.pointsForEnginCapacity(car)).thenReturn(0.005);
        when(calculator.pointsForAverageKMTraveledPerYear(car)).thenReturn(0.005);
        when(calculator.pointsForTypeOfVehicle(car)).thenReturn(0.005);

        double actualPoints = calculatePoints.calculatePointsForOC(car, client);

        assertEquals(expectedPoints, actualPoints);
    }
    @Test
    @DisplayName("Should return 0.22 points")
    void calculate_points2() {

        double expectedPoints = 0.022;

        when(calculator.pointsForClientAge(client)).thenReturn(0.005);
        when(calculator.pointsForVehicleAge(car)).thenReturn(0.007);
        when(calculator.pointsForEnginCapacity(car)).thenReturn(0.002);
        when(calculator.pointsForAverageKMTraveledPerYear(car)).thenReturn(0.005);
        when(calculator.pointsForTypeOfVehicle(car)).thenReturn(0.003);

        double actualPoints = calculatePoints.calculatePointsForOC(car, client);

        assertEquals(expectedPoints, actualPoints);
    }
}



