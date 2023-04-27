package com.carinsurance.client;

import com.carinsurance.car.CarRepository;
import com.carinsurance.car.Car;
import com.carinsurance.car.exception.CarNotFoundException;
import com.carinsurance.client.dto.ClientRequestDto;
import com.carinsurance.client.dto.ClientResponseDto;
import com.carinsurance.client.exception.ClientNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class ClientService {
    private final ClientMapper clientMapper;
    private final ClientRepository clientRepository;
    private final CarRepository carRepository;


    public Client saveClient(ClientRequestDto clientRequestDto) {
        Client client = clientRepository.save(clientMapper.dtoToEntity(clientRequestDto));
        return client;
    }


    public ClientResponseDto findClientById(Long id) {

        Client client = clientRepository.findById(id).orElseThrow(() -> new ClientNotFoundException(id));
        return clientMapper.entityToDto(client);
    }

    public Client assignCarToClient(Long clientId, Long carId) {
        Client client = clientRepository.findById(clientId).orElseThrow(() -> new ClientNotFoundException(clientId));
        Car car = carRepository.findById(carId).orElseThrow(() -> new CarNotFoundException(carId));
        client.getCars().add(car);
        return clientRepository.save(client);
    }

}
