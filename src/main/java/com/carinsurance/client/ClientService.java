package com.carinsurance.client;

import com.carinsurance.client.dto.ClientRequestDto;
import com.carinsurance.client.dto.ClientResponseDto;

import com.carinsurance.common.exception.exceptions.NotFoundException;
import lombok.AllArgsConstructor;

import lombok.extern.log4j.Log4j2;

import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
@Log4j2
public class ClientService implements ClientFacade {

    private final ClientRepository clientRepository;

    private final ClientMapper clientMapper;


    public Client saveClient(ClientRequestDto clientRequestDto) {
        log.info("Saving client {}", clientRequestDto);
        Client client = clientRepository.save(clientMapper.dtoToEntity(clientRequestDto));
        log.info("Saved client {}", client);
        return client;
    }

    public Client saveClientWithAddedCar(Client client) {
        clientRepository.save(client);
        return client;
    }

    public Client findById(Long id) {
        log.info("Finding client with ID {}", id);
        Client client = clientRepository.findById(id).orElseThrow(() -> new NotFoundException(id));
        log.info("Found client {}", client);
        return client;
    }

    public ClientResponseDto findClientById(Long id) {
        log.info("Finding client with ID {}", id);
        Client client = clientRepository.findById(id).orElseThrow(() -> new NotFoundException(id));
        log.info("Found client {}", client);
        return clientMapper.entityToDto(client);
    }

}
