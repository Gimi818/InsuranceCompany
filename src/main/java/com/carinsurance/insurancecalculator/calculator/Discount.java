package com.carinsurance.insurancecalculator.calculator;

import com.carinsurance.client.Client;
import org.springframework.stereotype.Component;

import static com.carinsurance.insurancecalculator.FinalNumbers.*;

@Component
public class Discount {


    public double calculateDiscountForInsuranceType(Client client, double discountMultiplier) {
        int carsListSize = client.getCars().size();
        if (carsListSize > 1) {
            return 1 - (carsListSize - 1) * discountMultiplier;
        } else {
            return 1;
        }
    }

    public double priceWithDiscountAc(double basicPrice, Client client) {
        return basicPrice * finalDiscountForAC(calculateDiscountForInsuranceType(client, AC_DISCOUNT));
    }

    public double priceWithDiscountOc(double basicPrice, Client client) {
        return basicPrice * finalDiscountForOC(calculateDiscountForInsuranceType(client, OC_DISCOUNT));
    }


    public double finalDiscountForOC(double discount) {
        return Math.max(discount, MAX_OC_DISCOUNT);
    }


    public double finalDiscountForAC(double discount) {
        return Math.max(discount, MAX_AC_DISCOUNT);
    }

}


