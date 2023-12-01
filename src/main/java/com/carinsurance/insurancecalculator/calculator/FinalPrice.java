package com.carinsurance.insurancecalculator.calculator;

import com.carinsurance.car.Car;
import com.carinsurance.car.CarFacade;


import com.carinsurance.client.Client;
import com.carinsurance.client.ClientFacade;

import com.carinsurance.insurancecalculator.PriceFormatter;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@Log4j2
public class FinalPrice {
    private final PriceFormatter priceFormatter;
    private final CarFacade carFacade;
    private final ClientFacade clientFacade;
    private final BasicPrice basicPrice;
    private final Discount discount;

    public double calculateFinalPrice(Long clientId, Long carId, boolean includeAc) {
        Car car = carFacade.findCarById(carId);
        Client client = clientFacade.findById(clientId);

        double finalPrice;

        if (includeAc) {
            double priceWithoutDiscount = basicPrice.calculateBasicPrice(car, client, true);
            finalPrice = discount.priceWithDiscountAc(priceWithoutDiscount, client);

        } else {
            double priceWithoutDiscount = basicPrice.calculateBasicPrice(car, client, false);
            finalPrice = discount.priceWithDiscountOc(priceWithoutDiscount, client);
            finalPrice = basicPrice.minimalPrice(finalPrice);
        }

        return priceFormatter.formatPrice(finalPrice);
    }


}


