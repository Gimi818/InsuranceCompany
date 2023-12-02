package com.carinsurance.client;

import com.carinsurance.client.dto.ClientRequestDto;
import com.carinsurance.client.dto.ClientResponseDto;


import com.carinsurance.client.dto.CreatedClientDto;
import com.carinsurance.common.exception.exceptions.NotFoundException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;


import org.mockito.junit.jupiter.MockitoExtension;

import static com.carinsurance.client.ClientService.ErrorMessages.CLIENT_NOT_FOUND;

import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.mockito.BDDMockito.*;


import java.util.*;


import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertThrows;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;



@ExtendWith(MockitoExtension.class)
class ClientServiceTest {

    @Mock
    private ClientRepository clientRepository;
    @Mock
    private ClientMapper clientMapper;
    @InjectMocks
    private ClientService service;
    @Mock
    private ClientRequestDto clientRequestDto;
    @Mock
    private ClientResponseDto clientResponseDto;

    @Mock
    private Client client;




    @BeforeEach
    void setUp() {
        clientRequestDto = new ClientRequestDto("John", "New", 30, Collections.emptyList());
        client = new Client("John", "New", 30, new ArrayList<>());
    }

    @Test
    @DisplayName("Should save client")
    void should_save_client() {
        given(clientRepository.save(clientMapper.dtoToEntity(clientRequestDto)))
                .willReturn(client);
        assertThat(service.saveClient(clientRequestDto))
                .isEqualTo(clientMapper.createdEntityToDto(client));
    }

    @Test
    @DisplayName("Should find client by ID")
    void should_find_client_by_id() {
        given(clientRepository.findById(1L)).willReturn(Optional.of(client));
        given(clientMapper.entityToDto(client))
                .willReturn(clientResponseDto);

        assertThat(service.findClientById(1L)).isEqualTo(clientResponseDto);
    }

    @Test
    @DisplayName("Should throw exception when not found client")
    void should_throw_exception_when_client_not_found() {
        // Given
        Long nonExistingClientId = 2L;

        when(clientRepository.findById(nonExistingClientId)).thenReturn(Optional.empty());

        // When and Then
        NotFoundException exception = assertThrows(NotFoundException.class,
                () -> service.findById(nonExistingClientId));

        assertEquals(CLIENT_NOT_FOUND, exception.getMessage());

        verify(clientRepository).findById(nonExistingClientId);
    }


}

