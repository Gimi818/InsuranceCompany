package com.carinsurance.insurancecalculator.calculator;

import com.carinsurance.client.Client;
import org.springframework.stereotype.Component;

import static com.carinsurance.insurancecalculator.FinalNumbers.*;

@Component
public class CalculateDiscount {

    public double calculateDiscountForOC(Client client) {
        int carsListSize = client.getCars().size();
        if (carsListSize > 1) {
            return 1 - (carsListSize - 1) * OC_DISCOUNT;
        } else {
            return 1;
        }
    }

    public double finalDiscountForOC(Double discount) {
        if (discount <= MAX_OC_DISCOUNT) {
            return discount = MAX_OC_DISCOUNT;
        } else return discount;


    }
    public double calculateDiscountForAC(Client client) {
        int carsListSize = client.getCars().size();
        if (carsListSize > 1) {
            return 1 - (carsListSize - 1) * AC_DISCOUNT;
        } else {
            return 1;
        }
    }

    public double finalDiscountForAC(double discount) {
        if (discount <= MAX_AC_DISCOUNT) {
            return discount = MAX_AC_DISCOUNT;
        } else return discount;
    }

}


