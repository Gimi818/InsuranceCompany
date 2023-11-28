package com.carinsurance.car;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

@Configuration
public class CarConfiguration {
    @Bean
    public CarFacade carFacade(@Lazy final CarService carService) {
        return carService;
    }
}
