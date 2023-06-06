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
    private final CalculateDiscount discounts;
    private final DecimalFormat decimalFormat = new DecimalFormat("#");


    public double calculateOcInsurancePrice(Car car, Client client) {
        log.info("Calculating OC price for car with ID {} and client with ID {}", car.getId(), client.getId());
        double finalPrice = ocPriceWithDiscount(basicOcInsurancePrice(car, client), client);
        if (finalPrice < MINIMAL_PRICE_FOR_INSURANCE) {
            finalPrice = MINIMAL_PRICE_FOR_INSURANCE;
        }
        log.info("Calculated insurance price is: {}", finalPrice);
        return Double.parseDouble(decimalFormat.format(finalPrice));
    }


    public double calculateAcOcInsurancePrice(Car car, Client client) {
        log.info("Calculating OC and AC price for car with ID {} and client with ID {}", car.getId(), client.getId());
        double ocPrice = calculateOcInsurancePrice(car, client);
        double acPrice = acPriceWithDiscount(basicAcInsurancePrice(car, client), client);
        double finalPrice = acPrice + ocPrice;
        log.info("Calculated OC and AC price is: {}", finalPrice);
        return Double.parseDouble(decimalFormat.format(finalPrice));

    }

    public double basicOcInsurancePrice(Car car, Client client) {
        return calculatePoints.calculatePointsForOC(car, client) * car.getCarValue();
    }

    public double basicAcInsurancePrice(Car car, Client client) {
        return calculatePoints.calculatePointsForAC(car, client) * car.getCarValue();
    }

    public double ocPriceWithDiscount(double basicPrice, Client client) {
        return basicPrice * discounts.finalDiscountForOC(client);
    }


    public double acPriceWithDiscount(double price, Client client) {
        return price * discounts.finalDiscountForAC(client);
    }

}

