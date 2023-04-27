package com.carinsurance.client;

import com.carinsurance.client.dto.ClientRequestDto;
import com.carinsurance.client.dto.ClientResponseDto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;


import static org.assertj.core.api.Assertions.assertThat;


@ExtendWith(MockitoExtension.class)
class ClientServiceTest {

    @Mock
    private ClientRepository clientRepository;

    @Mock
    private ClientMapper clientMapper;

    @InjectMocks
    private ClientService service;
    private ClientRequestDto clientRequestDto;
    private ClientResponseDto clientResponseDto;
    private Client client;


    @BeforeEach
    void setUp() {
        clientRequestDto = new ClientRequestDto("John", "New", 30, null);
        client = new Client(1L, "John", "New", 30, null);
    }

    @Test
    void saveClient() {
        BDDMockito.given(clientRepository.save(clientMapper.dtoToEntity(clientRequestDto)))
                .willReturn(client);
        assertThat(service.saveClient(clientRequestDto))
                .isEqualTo(client);
    }

    @Test
    void findClientById() {
        BDDMockito.given(clientRepository.findById(1L)).willReturn(Optional.of(client));
        BDDMockito.given(clientMapper.entityToDto(client))
                .willReturn(clientResponseDto);

        assertThat(service.findClientById(1L)).isEqualTo(clientResponseDto);
    }
}
