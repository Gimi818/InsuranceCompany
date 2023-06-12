package com.carinsurance.insurancecalculator.oc;

import com.carinsurance.client.Client;
import org.springframework.stereotype.Component;
import static com.carinsurance.insurancecalculator.FinalNumbers.*;
@Component
public class CalculateDiscountOC {


    public double calculateDiscountForOC(Client client) {
        int amountOfCars = client.getCars().size();
        if (amountOfCars > 1) {
            return 1 - (amountOfCars - 1) * OC_DISCOUNT_FOR_THE_NEXT_CAR;
        } else {
            return 1;
        }
    }

    public double finalDiscountForOC(Double discount) {
        if (discount <= MAX_OC_DISCOUNT) {
            return discount = MAX_OC_DISCOUNT;
        } else return discount;


    }

}


