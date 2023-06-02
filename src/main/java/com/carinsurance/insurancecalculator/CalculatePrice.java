package com.carinsurance.insurancecalculator;

import com.carinsurance.car.Car;
import com.carinsurance.client.Client;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import java.text.DecimalFormat;

import static com.carinsurance.insurancecalculator.FinalNumbers.*;

@Component
@AllArgsConstructor
@Log4j2
public class CalculatePrice {

    private final CalculatePoints calculatePoints;
    private final DecimalFormat decimalFormat = new DecimalFormat("#");


    public double calculateFinalPriceForOC(Car car, Client client) {
        log.info("Calculating OC price for car with ID {} and client with ID {}", car.getId(), client.getId());
        double price = calculatePoints.calculatePointsForOC(car, client) * car.getCarValue();
        if (price < MINIMAL_PRICE_FOR_INSURANCE) {
            price = MINIMAL_PRICE_FOR_INSURANCE;
        }
        log.info("Calculated insurance price is: {}", price);
        return Double.parseDouble(decimalFormat.format(price));
    }


    public double calculateFinalPriceForAcAndOc(Car car, Client client) {
        log.info("Calculating OC and AC price for car with ID {} and client with ID {}", car.getId(), client.getId());
        double priceForOC = calculateFinalPriceForOC(car, client);
        double finalPrice = calculatePoints.calculatePointsForAC(car, client) * car.getCarValue() + priceForOC;
        log.info("Calculated OC and AC price is: {}", finalPrice);
        return Double.parseDouble(decimalFormat.format(finalPrice));

    }


}

