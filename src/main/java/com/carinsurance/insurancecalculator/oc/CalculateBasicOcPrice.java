package com.carinsurance.insurancecalculator.oc;

import com.carinsurance.car.Car;
import com.carinsurance.client.Client;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import static com.carinsurance.insurancecalculator.FinalNumbers.MINIMAL_PRICE_FOR_INSURANCE;

@AllArgsConstructor
@Component
public class CalculateBasicOcPrice {
    private final CalculatePointsOC calculatePoints;
    private final CalculateDiscountOC discounts;

    public double basicInsurancePrice(Car car, Client client) {
        return calculatePoints.calculatePointsForOC(car, client) * car.getCarValue();
    }


    public double priceWithDiscount(double basicPrice, Client client) {
        return basicPrice * discounts.finalDiscountForOC(discounts.calculateDiscountForOC(client));
    }

    public double minimalPrice(Double insurancePrice) {
        if (insurancePrice < MINIMAL_PRICE_FOR_INSURANCE) {
            return insurancePrice = MINIMAL_PRICE_FOR_INSURANCE;
        } else return insurancePrice;
    }

}
