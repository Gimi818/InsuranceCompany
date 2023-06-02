package com.carinsurance.insurancecalculator;

import com.carinsurance.car.Car;
import com.carinsurance.client.Client;
import lombok.AllArgsConstructor;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@Log4j2
public class CalculatePoints {
    private final Calculator calculator;

    public double calculatePoints(Car car, Client client) {
        log.info("Calculating points for car with ID {} and client with ID {}", car.getId(), client.getId());
        double points = 0;
        points = calculator.pointsForClientAge(client)
                + calculator.pointsForVehicleAge(car)
                + calculator.pointsForEnginCapacity(car)
                + calculator.pointsForAverageKMTraveledPerYear(car)
                + calculator.pointsForTypeOfVehicle(car);
        log.info("Calculated points is: {}", points);
        return points;
    }

}
