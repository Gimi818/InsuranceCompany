package com.carinsurance.car;

import com.carinsurance.car.dto.CarRequestDto;
import com.carinsurance.car.dto.CarResponseDto;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.shaded.com.fasterxml.jackson.core.JsonProcessingException;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//@SpringBootTest
@WebMvcTest(controllers = CarController.class)
@WithMockUser
//@AutoConfigureMockMvc
class CarControllerTest {
    /** TO DO
     * fix integration test configurations
     */
    @MockBean
    private CarService carService;
    @Autowired
    private MockMvc mockMvc;
    private static CarRequestDto carRequestDto;
    private static String carRequestDtoJson;
    private static Car car;
    private static CarResponseDto carResponseDto;

    @BeforeEach
    void setUp() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        carRequestDto = new CarRequestDto("Bmw", "X5", 30000, null, 2010, 3.0, 21000);
        //objectMapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
        carRequestDtoJson = objectMapper.writeValueAsString(carRequestDto);
        carResponseDto = new CarResponseDto(1L, "Bmw", "X5", 30000, null, 2010, 3.0, 21000,null);

    }


    @Test
    void should_save_car() throws Exception {
        BDDMockito.given(carService.saveCar(carRequestDto)).willReturn(car);

        mockMvc.perform(post("/cars/add")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", Matchers.is(1)))
                .andExpect(jsonPath("$.brand", Matchers.is("Bmw")))
                .andExpect(jsonPath("$.model", Matchers.is("X5")))
                .andExpect(jsonPath("$.carValue", Matchers.is(30000)))
                .andExpect(jsonPath("$.yearOfManufacture", Matchers.is(2100)))
                .andExpect(jsonPath("$.enginCapacity", Matchers.is(3.0)))
                .andExpect(jsonPath("$.averageKmTraveledPerYear", Matchers.is(21000)));

    }

    @Test
    void should_find_car_by_id() throws Exception {
        BDDMockito.given(carService.findCarById(1L)).willReturn(carResponseDto);

        mockMvc.perform(get("/cars/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", Matchers.is(1)))
                .andExpect(jsonPath("$.brand", Matchers.is("Bmw")))
                .andExpect(jsonPath("$.model", Matchers.is("X5")))
                .andExpect(jsonPath("$.carValue", Matchers.is(30000)))
                .andExpect(jsonPath("$.yearOfManufacture", Matchers.is(2100)))
                .andExpect(jsonPath("$.enginCapacity", Matchers.is(3.0)))
                .andExpect(jsonPath("$.averageKmTraveledPerYear", Matchers.is(21000)));
    }
}
