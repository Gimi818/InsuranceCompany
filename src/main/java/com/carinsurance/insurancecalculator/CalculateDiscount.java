package com.carinsurance.insurancecalculator;

import com.carinsurance.client.Client;
import org.springframework.stereotype.Component;


@Component
public class CalculateDiscount {


    public double calculateDiscountForOC(Client client) {
        int amountOfCars = client.getCars().size();
        if (amountOfCars > 1) {
            return 1 - (amountOfCars - 1) * 0.05;
        } else {
            return 1;
        }
    }

    public double calculateDiscountForAC(Client client) {
        int amountOfCars = client.getCars().size();
        if (amountOfCars > 1) {
            return 1 - (amountOfCars - 1) * 0.02;
        } else {
            return 1;
        }
    }

    public double finalDiscountForOC(Client client) {
        double discount = calculateDiscountForOC(client);
        if (discount <= 0.60) {
            return discount = 0.60;
        } else return discount;


    }

    public double finalDiscountForAC(Client client) {
        double discount = calculateDiscountForAC(client);
        if (discount <= 0.75) {
            return discount = 0.75;
        } else return discount;
    }

}


