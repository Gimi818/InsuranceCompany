package com.carinsurance.insurancecalculator.calculator;

import com.carinsurance.car.Car;
import com.carinsurance.client.Client;
import com.carinsurance.insurancecalculator.Calculator;
import com.carinsurance.insurancecalculator.calculator.points.AcPoints;
import com.carinsurance.insurancecalculator.calculator.points.OcPoints;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import static com.carinsurance.insurancecalculator.FinalNumbers.*;


@Component
@AllArgsConstructor
public class BasicPrice {
    private final Discount discounts;
    private final AcPoints calculatorAc;
    private final OcPoints calculatorOc;

    public double calculateBasicPrice(Car car, Client client, boolean isAc) {

        double points;
        if (isAc) {
            points = calculatePointsForInsuranceType(car, client, calculatorAc)
                    + calculatePointsForInsuranceType(car, client, calculatorOc);
        } else {
            points = calculatePointsForInsuranceType(car, client, calculatorOc);
        }
        return points * car.getCarValue();
    }

    public double calculatePointsForInsuranceType(Car car, Client client, Calculator calculator) {
        return calculator.pointsForClientAge(client)
                + calculator.pointsForVehicleAge(car)
                + calculator.pointsForEnginCapacity(car)
                + calculator.pointsForParkingType(car)
                + calculator.pointsForAverageKMTraveledPerYear(car)
                + calculator.pointsForTypeOfVehicle(car);
    }

    public double minimalPrice(double insurancePrice) {
        return (insurancePrice < MINIMAL_PRICE_FOR_INSURANCE) ? MINIMAL_PRICE_FOR_INSURANCE : insurancePrice;
    }
}

