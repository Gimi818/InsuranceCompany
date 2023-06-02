package com.carinsurance.insurancecalculator;

import com.carinsurance.car.Car;
import com.carinsurance.client.Client;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import java.text.DecimalFormat;

@Component
@AllArgsConstructor
@Log4j2
public class CalculatePrice {

    private final CalculatePoints calculatePoints;
    private final DecimalFormat decimalFormat = new DecimalFormat("#");


    public double calculateFinalPrice(Car car, Client client) {
        log.info("Calculating insurance price for car with ID {} and client with ID {}", car.getId(), client.getId());
        double price = calculatePoints.calculatePoints(car, client) * car.getCarValue();
        if (price < 300) {
            price = 300;
        }
        log.info("Calculated insurance price is: {}", price);
        return Double.parseDouble(decimalFormat.format(price));
    }


}
