package com.carinsurance.insurancecalculator.ac;

import com.carinsurance.car.Car;
import com.carinsurance.client.Client;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@Log4j2
public class CalculatePointsAC {

    private final CalculatorAC calculatorAC;

    public double calculatePointsForAC(Car car, Client client) {
        log.info("Calculating AC points for car with ID {} and client with ID {}", car.getId(), client.getId());
        double points = calculatorAC.pointsForClientAge(client)
                + calculatorAC.pointsForVehicleAge(car)
                + calculatorAC.pointsForEnginCapacity(car)
                + calculatorAC.pointsForParkingType(car)
                + calculatorAC.pointsForAverageKMTraveledPerYear(car)
                + calculatorAC.pointsForTypeOfVehicle(car);
        log.info("Calculated AC points is: {}", points);
        return points;
    }

}
