package com.carinsurance.insurancecalculator.oc;

import com.carinsurance.car.Car;
import com.carinsurance.car.CarRepository;
import com.carinsurance.car.exception.CarNotFoundException;
import com.carinsurance.client.Client;
import com.carinsurance.client.ClientRepository;
import com.carinsurance.client.exception.ClientNotFoundException;
import com.carinsurance.insurancecalculator.PriceFormatter;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
@Log4j2
public class CalculateFinalOCPriceService {
    private final PriceFormatter priceFormatter;
    private final CarRepository carRepository;
    private final ClientRepository clientRepository;
    private final CalculateBasicOcPrice calculateBasicOcPrice;

    public double calculateOcInsurancePrice(Long clientId, Long carId) {
        log.info("Calculating OC price for car with ID {} and client with ID {}", clientId, carId);

        Client client = clientRepository.findById(clientId).orElseThrow(() -> new ClientNotFoundException(clientId));
        Car car = carRepository.findById(carId).orElseThrow(() -> new CarNotFoundException(carId));

        double finalPrice = calculateBasicOcPrice.priceWithDiscount(calculateBasicOcPrice.basicInsurancePrice(car, client), client);
        calculateBasicOcPrice.minimalPrice(finalPrice);

        log.info("Calculated insurance price is: {}", finalPrice);
        return priceFormatter.formatPrice(finalPrice);

    }


}

