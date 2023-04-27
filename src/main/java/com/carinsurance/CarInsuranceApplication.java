package com.carinsurance;

import com.carinsurance.security.JwtConfigurationProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(value = {JwtConfigurationProperties.class})

public class CarInsuranceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CarInsuranceApplication.class, args);
    }

}
