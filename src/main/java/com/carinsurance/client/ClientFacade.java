package com.carinsurance.client;


import com.carinsurance.client.dto.ClientRequestDto;
import com.carinsurance.client.dto.ClientResponseDto;

import java.util.List;

public interface ClientFacade {
    Client saveClient(ClientRequestDto clientRequestDto);
    ClientResponseDto findClientById(Long id);
    Client findById(Long id);
     Client saveClientWithAddedCar(Client client);

}
