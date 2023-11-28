package com.carinsurance.insurancecalculator.calculator;

import com.carinsurance.car.Car;
import com.carinsurance.client.Client;
import com.carinsurance.insurancecalculator.PriceFormatter;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import static com.carinsurance.insurancecalculator.FinalNumbers.MINIMAL_PRICE_FOR_INSURANCE;


@Component
@AllArgsConstructor
public class CalculateBasicPrice {
    private final CalculatePoints calculatePoints;
    private final CalculateDiscount discounts;
    private final PriceFormatter formatter;

    public double basicPriceOc(Car car, Client client) {
        return calculatePoints.calculatePointsForOC(car, client) * car.getCarValue();
    }

    public double basicPriceAc(Car car, Client client) {
        return calculatePoints.calculatePointsForAC(car, client) * car.getCarValue();
    }

    public double priceWithDiscount(double basicPrice, Client client) {
        return basicPrice * discounts.finalDiscountForOC(discounts.calculateDiscountForOC(client));
    }
    public double priceWithDiscountAc(double price, Client client) {
        return price * discounts.finalDiscountForAC(discounts.calculateDiscountForAC(client));
    }

    public double calculateAcInsurancePrice(Car car, Client client) {
        double finalAcPrice = priceWithDiscountAc(basicPriceAc(car, client), client);
        return formatter.formatPrice(finalAcPrice);

    }


    public double minimalPrice(Double insurancePrice) {
        if (insurancePrice < MINIMAL_PRICE_FOR_INSURANCE) {
            return insurancePrice = MINIMAL_PRICE_FOR_INSURANCE;
        } else return insurancePrice;
    }
}
