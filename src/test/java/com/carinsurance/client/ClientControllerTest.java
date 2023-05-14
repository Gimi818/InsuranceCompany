package com.carinsurance.client;


import com.carinsurance.client.dto.ClientRequestDto;
import com.carinsurance.client.dto.ClientResponseDto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;

import org.springframework.test.web.servlet.MockMvc;

import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.testcontainers.shaded.com.fasterxml.jackson.core.JsonProcessingException;
import org.testcontainers.shaded.com.fasterxml.jackson.core.type.TypeReference;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.SerializationFeature;
import java.util.*;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.assertj.core.api.Assertions.assertThat;
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
    private static Client client;
    private static ClientResponseDto clientResponseDto;
    private static ClientResponseDto secondClientResponseDto;

    @BeforeEach
    void setUp() throws JsonProcessingException {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);

        clientRequestDto = new ClientRequestDto("John", "Smith", 30, null);
        clientRequestDtoJson = objectMapper.writeValueAsString(clientRequestDto);
        clientResponseDto = new ClientResponseDto(1L, "John", "Smith", 30, null);
        secondClientResponseDto = new ClientResponseDto(2L, "Adam", "Nowak", 32, null);

    }

    @WithMockUser
    @Test
    void saveClient() throws Exception {
        given(clientService.saveClient(clientRequestDto)).willReturn(client);

        mockMvc.perform(post("/clients/add")
                        .content(clientRequestDtoJson)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    void should_find_client_by_id() throws Exception {
        given(clientService.findClientById(1L)).willReturn(clientResponseDto);

        mockMvc.perform(get("/clients/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.firstname").value("John"))
                .andExpect(jsonPath("$.lastname").value("Smith"))
                .andExpect(jsonPath("$.age").value(30));
    }

    @Test
    void should_find_all_clients() throws Exception {
        List<ClientResponseDto> clientResponseList = List.of(clientResponseDto, secondClientResponseDto);
        BDDMockito.given(clientService.findAllClient())
                .willReturn(clientResponseList);

        MvcResult mvcResult = mockMvc.perform(get("/clients"))
                .andExpect(status().isOk())
                .andReturn();

        String clientDtoJson = mvcResult.getResponse().getContentAsString();
        List<Client> clientsResult = new ObjectMapper()
                .readValue(clientDtoJson, new TypeReference<List<Client>>() {
                });
        assertThat(clientsResult).hasSize(2);
    }

}

