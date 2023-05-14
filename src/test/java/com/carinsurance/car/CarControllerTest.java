package com.carinsurance.car;

import com.carinsurance.car.dto.CarRequestDto;
import com.carinsurance.car.dto.CarResponseDto;


import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
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
    private static Car car;
    private static CarResponseDto carResponseDto;

    @BeforeEach
    void setUp() throws JsonProcessingException {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
        carRequestDto = new CarRequestDto("Bmw", "X5", 30000, CarModel.CAR, 2010, 3.0, 21000);

        carRequestDtoJson = objectMapper.writeValueAsString(carRequestDto);
        carResponseDto = new CarResponseDto(1L, "Bmw", "X5", 30000, CarModel.CAR, 2010, 3.0, 21000, null);

    }

    @Test
    void should_save_car() throws Exception {
        given(carService.saveCar(carRequestDto)).willReturn(car);

        mockMvc.perform(post("/cars/add")
                        .content(carRequestDtoJson)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    void should_find_car_by_id() throws Exception {
        given(carService.findCarById(1L)).willReturn(carResponseDto);

        mockMvc.perform(get("/cars/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", Matchers.is(1)))
                .andExpect(jsonPath("$.brand", Matchers.is("Bmw")))
                .andExpect(jsonPath("$.model", Matchers.is("X5")))
                .andExpect(jsonPath("$.carValue", Matchers.is(30000)))
                .andExpect(jsonPath("$.yearOfManufacture", Matchers.is(2010)))
                .andExpect(jsonPath("$.enginCapacity", Matchers.is(3.0)))
                .andExpect(jsonPath("$.averageKmTraveledPerYear", Matchers.is(21000)));
    }
    @Test
    void should_assign_policy_to_car() throws Exception {
        // given
        Long carId = 1L;
        Long policyId = 2L;
        // when
        mockMvc.perform(put("/cars/" + carId + "/policies/" + policyId))
                .andExpect(status().isOk());
        // then
        verify(carService,times(1)).assignPolicyToCar(carId, policyId);
    }

}
