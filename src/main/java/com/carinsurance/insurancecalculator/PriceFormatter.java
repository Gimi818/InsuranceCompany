package com.carinsurance.insurancecalculator;

import org.springframework.stereotype.Component;

import java.text.DecimalFormat;
@Component
public class PriceFormatter {

    private final DecimalFormat decimalFormat;

    public PriceFormatter() {
        this.decimalFormat = new DecimalFormat("#");
    }

    public double formatPrice(double price) {
        return Double.parseDouble(decimalFormat.format(price));
    }
}

