package com.carinsurance.policy;


import com.carinsurance.car.CarModel;
import com.carinsurance.car.dto.CarResponseDto;
import com.carinsurance.client.dto.ClientResponseDto;
import com.carinsurance.policy.dto.PolicyRequestDto;
import com.carinsurance.policy.dto.PolicyResponseDto;
import com.fasterxml.jackson.core.JsonProcessingException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.SerializationFeature;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest("WebEnvironment.RANDOM_PORT")
@WithMockUser
public class PolicyControllerTest {
    @MockBean
    private PolicyService policyService;
    @Autowired
    private WebApplicationContext context;
    private MockMvc mockMvc;

    private static Policy policy;
    private static PolicyResponseDto policyResponseDto;
    private static String clientRequestDtoJson;
    private static String policyRequestDtoJson;
    private static PolicyRequestDto policyRequestDto;
    private static CarResponseDto carResponseDto;
    private static ClientResponseDto clientResponseDto;


    @BeforeEach
    void setUp() throws JsonProcessingException {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);

        policyResponseDto = new PolicyResponseDto(1L, 1000, LocalDate.now(), LocalDate.now().plusYears(1));
        policyRequestDto = new PolicyRequestDto(1000, LocalDate.now(), LocalDate.now().plusYears(1));

    }

    @Test
    void should_find_policy_by_id() throws Exception {
        given(policyService.findPolicyById(1L)).willReturn(policyResponseDto);
        mockMvc.perform(get("/policies/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.priceOfInsurance").value(1000))
                .andExpect(jsonPath("$.startDate").value(LocalDate.now().format(DateTimeFormatter.ISO_LOCAL_DATE)))
                .andExpect(jsonPath("$.endDate").value(LocalDate.now().plusYears(1).format(DateTimeFormatter.ISO_LOCAL_DATE)));
    }

    @Test
    public void shouldSavePolicy() throws Exception {
        // given
        carResponseDto = new CarResponseDto(1L, "Bmw", "X5", 30000, CarModel.CAR, 2010, 3.0, 21000, null);
        clientResponseDto = new ClientResponseDto(1L, "John", "Smith", 30, null);


        given(policyService.savePolicy(clientResponseDto.id(), carResponseDto.id())).willReturn(policy);

        mockMvc.perform(post("/policies/1/cars/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());


    }
}
