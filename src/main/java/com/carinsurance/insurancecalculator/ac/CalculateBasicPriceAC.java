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
public class CalculateBasicPriceAC {

    private final CalculatePointsAC calculatePoints;
    private final CalculateDiscountAC discounts;
    private final PriceFormatter formatter;


    public double calculateAcInsurancePrice(Car car, Client client) {
        double finalAcPrice = priceWithDiscount(basicInsurancePrice(car, client), client);
        log.info("Calculated AC price is: {}", finalAcPrice);
        return formatter.formatPrice(finalAcPrice);

    }

    public double basicInsurancePrice(Car car, Client client) {
        return calculatePoints.calculatePointsForAC(car, client) * car.getCarValue();
    }


    public double priceWithDiscount(double price, Client client) {
        return price * discounts.finalDiscountForAC(discounts.calculateDiscountForAC(client));
    }
}
