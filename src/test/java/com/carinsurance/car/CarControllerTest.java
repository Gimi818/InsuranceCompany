package com.carinsurance.car;

import com.carinsurance.car.dto.CarRequestDto;
import com.carinsurance.car.dto.CreatedCarDto;


import com.carinsurance.car.enums.CarModel;
import com.carinsurance.car.enums.ParkingType;
import com.carinsurance.client.Client;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.testcontainers.shaded.com.fasterxml.jackson.core.JsonProcessingException;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.SerializationFeature;

import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest("WebEnvironment.RANDOM_PORT")
@WithMockUser
class CarControllerTest {

    @MockBean
    private CarService carService;

    @Autowired
    private WebApplicationContext context;
    private MockMvc mockMvc;

    private static CarRequestDto carRequestDto;
    private static String carRequestDtoJson;
    private static CreatedCarDto createdCarDto;
    private static Client client;

    @BeforeEach
    void setUp() throws JsonProcessingException {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
        carRequestDto = new CarRequestDto("Bmw", "X5", 30000, CarModel.CAR, ParkingType.GARAGE, 2010, 3.0, 21000);
        client = new Client();
        carRequestDtoJson = objectMapper.writeValueAsString(carRequestDto);
        createdCarDto = new CreatedCarDto("Bmw", "X5", 2010);

    }

    @Test
    @DisplayName("Should save car")
    void should_save_car() throws Exception {
        given(carService.saveCar(carRequestDto, client.getId())).willReturn(createdCarDto);

        mockMvc.perform(post("/cars/1")
                        .content(carRequestDtoJson)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

}
