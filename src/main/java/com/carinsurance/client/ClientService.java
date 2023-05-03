package com.carinsurance.client;

import com.carinsurance.car.CarRepository;
import com.carinsurance.car.Car;
import com.carinsurance.car.exception.CarNotFoundException;
import com.carinsurance.client.dto.ClientRequestDto;
import com.carinsurance.client.dto.ClientResponseDto;
import com.carinsurance.client.exception.ClientNotFoundException;
import lombok.AllArgsConstructor;

import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor
@Log4j2
public class ClientService {

    private final ClientRepository clientRepository;

    private final ClientMapper clientMapper;
    private final CarRepository carRepository;


    public Client saveClient(ClientRequestDto clientRequestDto) {
        log.info("Saving client {}", clientRequestDto);
        Client client = clientRepository.save(clientMapper.dtoToEntity(clientRequestDto));
        log.info("Saved client {}", client);
        return client;
    }


    public ClientResponseDto findClientById(Long id) {
        log.info("Finding client with ID {}", id);
        Client client = clientRepository.findById(id).orElseThrow(() -> new ClientNotFoundException(id));
        log.info("Found client {}", client);
        return clientMapper.entityToDto(client);
    }

    public List<ClientResponseDto> findAllClient() {
        log.info("Finding all clients...");
        return clientRepository.findAllClients().stream()
                .map(clientMapper::entityToDto)
                .collect(Collectors.toList());
    }


    public Client assignCarToClient(Long clientId, Long carId) {
        log.info("Assigning car with ID {} to client with ID {}", carId, clientId);
        Client client = clientRepository.findById(clientId).orElseThrow(() -> new ClientNotFoundException(clientId));
        Car car = carRepository.findById(carId).orElseThrow(() -> new CarNotFoundException(carId));
            client.getCars().add(car);
        log.info("Assigned car with ID {} to client with ID {}", carId, clientId);
        return clientRepository.save(client);
    }

}
