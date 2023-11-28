package com.carinsurance.insurancecalculator.acoc;

import com.carinsurance.car.Car;
import com.carinsurance.car.CarFacade;

import com.carinsurance.client.Client;
import com.carinsurance.client.ClientFacade;

import com.carinsurance.insurancecalculator.PriceFormatter;
import com.carinsurance.insurancecalculator.ac.CalculateBasicPriceAC;
import com.carinsurance.insurancecalculator.oc.CalculateBasicOcPrice;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Log4j2
public class CalculateFinalAcOcPriceService {
    private final CarFacade carFacade;
    private final ClientFacade clientFacade;
    private final CalculateBasicPriceAC calculatePriceAC;
    private final CalculateBasicOcPrice calculatePriceOC;
    private final PriceFormatter priceFormatter;


    public double finalAcOcInsurancePrice(Long clientId, Long carId) {
        Car car = carFacade.findCarById(carId);
        Client client = clientFacade.findById(clientId);

        double finalPrice = calculatePriceAC.calculateAcInsurancePrice(car, client) +
                calculatePriceOC.priceWithDiscount(calculatePriceOC.basicInsurancePrice(car, client), client);

        log.info("Calculated OC and AC price is: {}", finalPrice);
        return priceFormatter.formatPrice(finalPrice);

    }

}
