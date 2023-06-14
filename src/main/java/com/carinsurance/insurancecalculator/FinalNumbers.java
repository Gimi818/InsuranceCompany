package com.carinsurance.insurancecalculator;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class FinalNumbers {
    public static final int LOWER_AGE_LIMIT = 26;
    public static final int MIDDLE_AGE_LIMIT = 65;
    public static final double LOWER_ENGINE_CAPACITY_LIMIT = 1.6;
    public static final double MIDDLE_ENGINE_CAPACITY_LIMIT = 2.5;
    public static final double HIGHER_ENGINE_CAPACITY_LIMIT = 3.5;
    public static final int LOWER_LIMIT_YEAR_OF_MANUFACTURE = 2020;
    public static final int MIDDLE_LIMIT_YEAR_OF_MANUFACTURE = 2015;
    public static final int HIGHER_LIMIT_YEAR_OF_MANUFACTURE = 2010;
    public static final int LOWER_LIMIT_KM_PER_YEAR = 15000;
    public static final int MIDDLE_LIMIT_KM_PER_YEAR = 30000;
    public static final double MINIMAL_PRICE_FOR_INSURANCE = 300.0;
    public static final double MAX_OC_DISCOUNT = 0.60;
    public static final double MAX_AC_DISCOUNT = 0.75;
    public static final double OC_DISCOUNT = 0.05;
    public static final double AC_DISCOUNT = 0.02;

}
