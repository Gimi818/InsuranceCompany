package com.carinsurance.insurancecalculator.ac;

import com.carinsurance.car.Car;
import com.carinsurance.client.Client;
import com.carinsurance.insurancecalculator.PriceFormatter;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
@Log4j2
public class CalculatePriceAC {

    private final CalculatePointsAC calculatePoints;
    private final CalculateDiscountAC discounts;
    private final PriceFormatter priceFormatter;


    public double calculateAcInsurancePrice(Car car, Client client) {
        log.info("Calculating  AC price for car with ID {} and client with ID {}", car.getId(), client.getId());
        double finalAcPrice = acPriceWithDiscount(basicAcInsurancePrice(car, client), client);
        log.info("Calculated AC price is: {}", finalAcPrice);
        return priceFormatter.formatPrice(finalAcPrice);

    }

    public double basicAcInsurancePrice(Car car, Client client) {
        return calculatePoints.calculatePointsForAC(car, client) * car.getCarValue();
    }


    public double acPriceWithDiscount(double price, Client client) {
        return price * discounts.finalDiscountForAC(discounts.calculateDiscountForAC(client));
    }
}
