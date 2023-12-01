package com.carinsurance.client;

import com.carinsurance.client.dto.ClientRequestDto;
import com.carinsurance.client.dto.ClientResponseDto;

import com.carinsurance.client.dto.CreatedClientDto;
import com.carinsurance.common.exception.exceptions.NotFoundException;
import lombok.AllArgsConstructor;

import lombok.extern.log4j.Log4j2;
 import static com.carinsurance.client.ClientService.ErrorMessages.*;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
@Log4j2
public class ClientService implements ClientFacade {

    private final ClientRepository clientRepository;

    private final ClientMapper clientMapper;


    public CreatedClientDto saveClient(ClientRequestDto clientRequestDto) {
        Client client = clientRepository.save(clientMapper.dtoToEntity(clientRequestDto));
        log.info("Saved client {} {}", client.getFirstname() , client.getLastname());
        return clientMapper.createdEntityToDto(client);
    }

    public Client saveClientWithAddedCar(Client client) {
        clientRepository.save(client);
        return client;
    }

    public Client findById(Long id) {
        return clientRepository.findById(id).orElseThrow(() -> new NotFoundException(CLIENT_NOT_FOUND));
    }

    public ClientResponseDto findClientById(Long id) {
        Client client = clientRepository.findById(id).orElseThrow(() -> new NotFoundException(CLIENT_NOT_FOUND));
        log.info("Found client with Id {}", id);
        return clientMapper.entityToDto(client);
    }


    static final class ErrorMessages {

        static final String CLIENT_NOT_FOUND = "Client with id %d not found";

    }

}
