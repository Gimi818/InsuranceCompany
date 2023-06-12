package com.carinsurance.insurancecalculator.acoc;

import com.carinsurance.car.Car;
import com.carinsurance.client.Client;
import com.carinsurance.insurancecalculator.PriceFormatter;
import com.carinsurance.insurancecalculator.ac.CalculatePriceAC;
import com.carinsurance.insurancecalculator.oc.CalculatePriceOC;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
@Log4j2
public class CalculateAcOcPrice {
    private final CalculatePriceAC calculatePriceAC;
    private final CalculatePriceOC calculatePriceOC;
    private final PriceFormatter priceFormatter;

    public double finalAcOcInsurancePrice(Client client, Car car) {
        log.info("Calculating OC and AC price for car with ID {} and client with ID {}", car.getId(), client.getId());
        double finalPrice = calculatePriceAC.calculateAcInsurancePrice(car, client) +
                calculatePriceOC.calculateOcInsurancePrice(car, client);
         log.info("Calculated OC and AC price is: {}", finalPrice);
        return priceFormatter.formatPrice(finalPrice);

    }
}
