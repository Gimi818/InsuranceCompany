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
public class CalculateFinalPrice {
    private final PriceFormatter priceFormatter;
    private final CalculateBasicPrice calculateBasicOcPrice;
    private final CarFacade carFacade;
    private final ClientFacade clientFacade;
    private final CalculateBasicPrice calculatePriceAC;
    private final CalculateBasicPrice calculatePriceOC;


    public double finalOcPrice(Long clientId, Long carId) {
        Car car = carFacade.findCarById(carId);
        Client client = clientFacade.findById(clientId);
        double finalPrice = calculatePriceOC.priceWithDiscount(calculatePriceOC.basicPriceOc(car, client), client);
        calculateBasicOcPrice.minimalPrice(finalPrice);
        return priceFormatter.formatPrice(finalPrice);

    }

    public double finalAcOcPrice(Long clientId, Long carId) {
        Car car = carFacade.findCarById(carId);
        Client client = clientFacade.findById(clientId);
        double finalPrice = calculatePriceAC.calculateAcInsurancePrice(car, client) +
                calculatePriceOC.priceWithDiscount(calculatePriceOC.basicPriceOc(car, client), client);
        return priceFormatter.formatPrice(finalPrice);

    }

}

