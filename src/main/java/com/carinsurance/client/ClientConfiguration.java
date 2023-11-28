package com.carinsurance.client;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
@Configuration
public class ClientConfiguration {

    @Bean
    public ClientFacade clientFacade(@Lazy final ClientService clientService) {
        return clientService;
    }
}

