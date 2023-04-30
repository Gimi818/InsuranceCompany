package com.carinsurance.client;

import com.carinsurance.car.Car;
import com.carinsurance.car.CarRepository;
import com.carinsurance.car.CarService;
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
    private CarRepository carRepository;
    @Mock
    private ClientMapper clientMapper;

    @InjectMocks
    private ClientService service;

    private ClientRequestDto clientRequestDto;
    private ClientResponseDto clientResponseDto;
    private Client client;
    private Car car;


    @BeforeEach
    void setUp() {
        clientRequestDto = new ClientRequestDto("John", "New", 30, Collections.emptyList());
        client = new Client(1L, "John", "New", 30, Collections.emptyList());
        car = new Car(1L, "test", "test", 0, null, 0, 0, 0, null);

    }

    @Test
    void saveClient() {
        BDDMockito.given(clientRepository.save(clientMapper.dtoToEntity(clientRequestDto)))
                .willReturn(client);
        assertThat(service.saveClient(clientRequestDto))
                .isEqualTo(client);
    }

    @Test
    void should_find_client_by_id() {
        BDDMockito.given(clientRepository.findById(1L)).willReturn(Optional.of(client));
        BDDMockito.given(clientMapper.entityToDto(client))
                .willReturn(clientResponseDto);

        assertThat(service.findClientById(1L)).isEqualTo(clientResponseDto);
    }


    @Test
    void should_assign_car_to_client() {
        // given

        BDDMockito.given(clientRepository.findById(client.getId())).willReturn(Optional.of(client));
        BDDMockito.given(carRepository.findById(car.getId())).willReturn(Optional.of(car));
        // when
        Client result = service.assignCarToClient(client.getId(), car.getId());
        // then
        assertThat(result).isNotNull();
        assertThat(result.getCars()).hasSize(1);

        BDDMockito.verify(clientRepository, BDDMockito.times(1)).save(client);
    }

}
