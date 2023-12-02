package com.carinsurance.policy;


import com.carinsurance.policy.dto.PolicyRequestDto;
import com.carinsurance.policy.dto.PolicyResponseDto;
import com.fasterxml.jackson.core.JsonProcessingException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.SerializationFeature;

import java.time.LocalDate;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest("WebEnvironment.RANDOM_PORT")
@WithMockUser
 class PolicyControllerTest {
    @MockBean
    private PolicyService policyService;
    @Autowired
    private WebApplicationContext context;
    @Mock
    private MockMvc mockMvc;
    @Mock
    private static PolicyResponseDto policyResponseDto;
    @Mock
    private static PolicyRequestDto policyRequestDto;


    @BeforeEach
    void setUp() throws JsonProcessingException {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);

        policyResponseDto = new PolicyResponseDto(1000, PolicyType.OC, LocalDate.now(), LocalDate.now().plusYears(1));
        policyRequestDto = new PolicyRequestDto(1000, PolicyType.OC, LocalDate.now(), LocalDate.now().plusYears(1));

    }


    @Test
    @DisplayName("Should save OC policy")
    void should_save_OC_policy() throws Exception {
        // Given
        Long clientId = 1L;
        when(policyService.saveOCPolicy(clientId)).thenReturn(policyResponseDto);

        // When
        mockMvc.perform(post(PolicyController.Routes.OC, clientId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.priceOfInsurance").value(policyResponseDto.priceOfInsurance()));
        // Then
        verify(policyService).saveOCPolicy(clientId);
    }

    @Test
    @DisplayName("Should save OC/AC policy")
    void should_save_AC_policy() throws Exception {
        // Given
        Long clientId = 2L;
        when(policyService.saveACAndOCPolicy(clientId)).thenReturn(policyResponseDto);

        // When
        mockMvc.perform(post(PolicyController.Routes.OCAC, clientId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.priceOfInsurance").value(policyResponseDto.priceOfInsurance()));
        // Then
        verify(policyService).saveACAndOCPolicy(clientId);
    }

}
