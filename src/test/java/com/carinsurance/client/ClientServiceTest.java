package com.carinsurance.client;

import com.carinsurance.car.Car;
import com.carinsurance.car.CarRepository;
import com.carinsurance.client.dto.ClientRequestDto;
import com.carinsurance.client.dto.ClientResponseDto;

import com.carinsurance.client.exception.ClientNotFoundException;
import com.carinsurance.policy.Policy;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;


import java.util.*;



import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.client.ExpectedCount.times;


@ExtendWith(MockitoExtension.class)
class ClientServiceTest {

    @Mock
    private ClientRepository clientRepository;
    @Mock
    private CarRepository carRepository;
    @Mock
    private ClientMapper clientMapper;

    @InjectMocks
    private ClientService service;
    @Mock
    private ClientRequestDto clientRequestDto;
    @Mock
    private ClientResponseDto clientResponseDto;
    @Mock
    private ClientResponseDto secondClientResponseDto;
    @Mock
    private Client client;
    @Mock
    private Client secondClient;
    @Mock
    private Car car;


    @BeforeEach
    void setUp() {
        clientRequestDto = new ClientRequestDto("John", "New", 30, Collections.emptyList());
        client = new Client(1L, "John", "New", 30, new ArrayList<>());
        car = new Car(1L, "test", "test", 0, null,null, 0, 0, 0, null);

    }

    @Test
    void should_save_client() {
       given(clientRepository.save(clientMapper.dtoToEntity(clientRequestDto)))
                .willReturn(client);
        assertThat(service.saveClient(clientRequestDto))
                .isEqualTo(client);
    }

    @Test
    void should_find_client_by_id() {
        given(clientRepository.findById(1L)).willReturn(Optional.of(client));
        given(clientMapper.entityToDto(client))
                .willReturn(clientResponseDto);

        assertThat(service.findClientById(1L)).isEqualTo(clientResponseDto);
    }

    @Test
    void should_find_all_clients() {
        //given
        secondClient = new Client(2L, "Adam", "New", 32, null);
        clientResponseDto = new ClientResponseDto(1L, "John", "New", 30, null);
        secondClientResponseDto = new ClientResponseDto(2L, "Adam", "New", 32, null);

        List<Client> clientsList = List.of(client, secondClient);
        List<ClientResponseDto> expectedClientsDtoList = List.of(clientResponseDto, secondClientResponseDto);

        given(clientRepository.findAllClients()).willReturn(clientsList);
        given(clientMapper.entityToDto(client)).willReturn(clientResponseDto);
        given(clientMapper.entityToDto(secondClient)).willReturn(secondClientResponseDto);
        //when
        List<ClientResponseDto> actualClientsDtoList = service.findAllClient();
        //then
        Assertions.assertThat(expectedClientsDtoList).isEqualTo(actualClientsDtoList);
        Mockito.verify(clientMapper, Mockito.times(1)).entityToDto(client);
        Mockito.verify(clientMapper, Mockito.times(1)).entityToDto(secondClient);
    }


    @Test
    void should_return_empty_list_when_no_clients() {
        // given
        given(clientRepository.findAllClients()).willReturn(Collections.emptyList());
        // when
        List<ClientResponseDto> actualClientsDtoList = service.findAllClient();
        // then
        Assertions.assertThat(actualClientsDtoList).isEmpty();
    }

    @Test
    @DisplayName("Should throw ClientNotFoundException when client is not found")
    void should_throw_ClientNotFoundException() {
        // Given
        Long clientId = 1L;
        //when
        when(clientRepository.findById(clientId)).thenReturn(Optional.empty());
        //Then
        assertThrows(ClientNotFoundException.class, () -> service.findClientById(clientId));
    }

    @Test
    void should_assign_car_to_client() {
        // Given
        Long clientId = 1L;
        Long carId = 2L;
        Client client = new Client();
        Car car = new Car();
        //When
        when(clientRepository.findById(clientId)).thenReturn(Optional.of(client));
        when(carRepository.findById(carId)).thenReturn(Optional.of(car));
        when(clientRepository.save(client)).thenReturn(client);

        Client result = service.assignCarToClient(clientId, carId);

        // Then
        assertTrue(result.getCars().contains(car));
        assertThat(result).isNotNull();
        assertThat(result.getCars()).hasSize(1);

    }

}

