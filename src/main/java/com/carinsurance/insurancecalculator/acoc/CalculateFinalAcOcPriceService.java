package com.carinsurance.insurancecalculator.acoc;

import com.carinsurance.car.Car;
import com.carinsurance.car.CarRepository;
import com.carinsurance.car.exception.CarNotFoundException;
import com.carinsurance.client.Client;
import com.carinsurance.client.ClientRepository;
import com.carinsurance.client.exception.ClientNotFoundException;
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
    private final CalculateBasicPriceAC calculatePriceAC;
    private final CalculateBasicOcPrice calculatePriceOC;
    private final PriceFormatter priceFormatter;
    private final CarRepository carRepository;
    private final ClientRepository clientRepository;

    public double finalAcOcInsurancePrice(Long clientId, Long carId) {

        Car car = carRepository.findById(carId).orElseThrow(() -> new CarNotFoundException(carId));
        Client client = clientRepository.findById(clientId).orElseThrow(() -> new ClientNotFoundException(clientId));

        log.info("Calculating OC and AC price for car with ID {} and client with ID {}", car.getId(), client.getId());

        double finalPrice = calculatePriceAC.calculateAcInsurancePrice(car, client) +
                calculatePriceOC.priceWithDiscount(calculatePriceOC.basicInsurancePrice(car, client), client);

        log.info("Calculated OC and AC price is: {}", finalPrice);
        return priceFormatter.formatPrice(finalPrice);

    }

}
