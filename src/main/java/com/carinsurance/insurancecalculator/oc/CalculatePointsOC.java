package com.carinsurance.insurancecalculator.oc;

import com.carinsurance.car.Car;
import com.carinsurance.client.Client;
import lombok.AllArgsConstructor;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@Log4j2
public class CalculatePointsOC {
    private final CalculatorOC calculatorOC;

    public double calculatePointsForOC(Car car, Client client) {
        log.info("Calculating OC points for car with ID {} and client with ID {}", car.getId(), client.getId());
        double points = calculatorOC.pointsForClientAge(client)
                + calculatorOC.pointsForVehicleAge(car)
                + calculatorOC.pointsForEnginCapacity(car)
                + calculatorOC.pointsForParkingType(car)
                + calculatorOC.pointsForAverageKMTraveledPerYear(car)
                + calculatorOC.pointsForTypeOfVehicle(car);
        log.info("Calculated OC points is: {}", points);
        return points;
    }


}
