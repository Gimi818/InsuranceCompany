package com.carinsurance.insurancecalculator.oc;

import com.carinsurance.car.Car;
import com.carinsurance.client.Client;
import com.carinsurance.insurancecalculator.PriceFormatter;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import static com.carinsurance.insurancecalculator.FinalNumbers.*;

@Component
@AllArgsConstructor
@Log4j2
public class CalculatePriceOC {

    private final CalculatePointsOC calculatePoints;
    private final CalculateDiscountOC discounts;
    private final PriceFormatter priceFormatter;

    public double calculateOcInsurancePrice(Car car, Client client) {
        log.info("Calculating OC price for car with ID {} and client with ID {}", car.getId(), client.getId());
        double finalPrice = ocPriceWithDiscount(basicOcInsurancePrice(car, client), client);
        minimalPrice(finalPrice);
        log.info("Calculated insurance price is: {}", finalPrice);
        return priceFormatter.formatPrice(finalPrice);

    }

    public double basicOcInsurancePrice(Car car, Client client) {
        return calculatePoints.calculatePointsForOC(car, client) * car.getCarValue();
    }


    public double ocPriceWithDiscount(double basicPrice, Client client) {
        return basicPrice * discounts.finalDiscountForOC(discounts.calculateDiscountForOC(client));
    }

    public double minimalPrice(Double price) {
        if (price < MINIMAL_PRICE_FOR_INSURANCE) {
            return price = MINIMAL_PRICE_FOR_INSURANCE;
        } else return price;
    }


}

