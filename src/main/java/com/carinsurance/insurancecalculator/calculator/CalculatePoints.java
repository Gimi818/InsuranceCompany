package com.carinsurance.insurancecalculator.calculator;

import com.carinsurance.car.Car;
import com.carinsurance.client.Client;
import lombok.AllArgsConstructor;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@Log4j2
public class CalculatePoints {
    private final CalculatorOC calculatorOC;
    private final CalculatorAC calculatorAC;

    public double calculatePointsForOC(Car car, Client client) {
        return calculatorOC.pointsForClientAge(client)
                + calculatorOC.pointsForVehicleAge(car)
                + calculatorOC.pointsForEnginCapacity(car)
                + calculatorOC.pointsForParkingType(car)
                + calculatorOC.pointsForAverageKMTraveledPerYear(car)
                + calculatorOC.pointsForTypeOfVehicle(car);

    }

    public double calculatePointsForAC(Car car, Client client) {

        return calculatorAC.pointsForClientAge(client)
                + calculatorAC.pointsForVehicleAge(car)
                + calculatorAC.pointsForEnginCapacity(car)
                + calculatorAC.pointsForParkingType(car)
                + calculatorAC.pointsForAverageKMTraveledPerYear(car)
                + calculatorAC.pointsForTypeOfVehicle(car);
    }

}
