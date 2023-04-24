package com.carinsurance.client;

import com.carinsurance.client.dto.ClientRequestDto;
import com.carinsurance.client.dto.ClientResponseDto;
import com.carinsurance.client.exception.ClientNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import static com.carinsurance.client.ClientMapper.clientMapper;

@Service
@AllArgsConstructor
public class ClientService {

    private final ClientRepository clientRepository;


    public Client saveClient(ClientRequestDto clientRequestDto) {
        Client newClient = clientRepository.save(clientMapper.dtoToEntity(clientRequestDto));
        return newClient;
    }


    public ClientResponseDto findClientById(Long id) {

        Client client = clientRepository.findById(id).orElseThrow(()->new ClientNotFoundException(id));
        return clientMapper.entityToDto(client);
    }

}
