package com.carinsurance.insurancecalculator.ac;

import com.carinsurance.client.Client;
import org.springframework.stereotype.Component;
import static com.carinsurance.insurancecalculator.FinalNumbers.*;
@Component
public class CalculateDiscountAC {

    public double calculateDiscountForAC(Client client) {
        int amountOfCars = client.getCars().size();
        if (amountOfCars > 1) {
            return 1 - (amountOfCars - 1) * AC_DISCOUNT_FOR_THE_NEXT_CAR;
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
