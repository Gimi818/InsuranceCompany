package com.carinsurance.client;


import com.carinsurance.client.dto.ClientRequestDto;
import com.carinsurance.client.dto.ClientResponseDto;

import com.carinsurance.client.dto.CreatedClientDto;
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


import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest("WebEnvironment.RANDOM_PORT")
@WithMockUser
class ClientControllerTest {

    @MockBean
    private ClientService clientService;
    @Autowired
    private WebApplicationContext context;
    private MockMvc mockMvc;

    private static ClientRequestDto clientRequestDto;
    private static String clientRequestDtoJson;
    private static ClientResponseDto clientResponseDto;
    private static CreatedClientDto createdClientDto;


    @BeforeEach
    void setUp() throws JsonProcessingException {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);

        clientRequestDto = new ClientRequestDto("John", "Smith", 30, null);
        clientRequestDtoJson = objectMapper.writeValueAsString(clientRequestDto);
        clientResponseDto = new ClientResponseDto("John", "Smith", 30, null);


    }

    @WithMockUser
    @Test
    @DisplayName("Should save user")
    void save_client() throws Exception {
        given(clientService.saveClient(clientRequestDto)).willReturn(createdClientDto);

        mockMvc.perform(post("/clients")
                        .content(clientRequestDtoJson)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    @DisplayName("Should find client by ID")
    void should_find_client_by_id() throws Exception {
        given(clientService.findClientById(1L)).willReturn(clientResponseDto);

        mockMvc.perform(get("/clients/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstname").value("John"))
                .andExpect(jsonPath("$.lastname").value("Smith"))
                .andExpect(jsonPath("$.age").value(30));
    }


}

