package com.carinsurance.policy;

import com.carinsurance.car.Car;
import com.carinsurance.client.Client;
import com.carinsurance.insurancecalculator.acoc.CalculateFinalAcOcPriceService;
import com.carinsurance.insurancecalculator.oc.CalculateFinalOCPriceService;
import com.carinsurance.insurancecalculator.UniqueStringGenerator;
import com.carinsurance.policy.dto.PolicyResponseDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;


@ExtendWith(MockitoExtension.class)
class PolicyServiceTest {
    @Mock
    private PolicyRepository policyRepository;
    @Mock
    private PolicyMapper policyMapper;
    @Mock
    private UniqueStringGenerator stringGenerator;
    @Mock
    private CalculateFinalOCPriceService finalOcPrice;
    @Mock
    private CalculateFinalAcOcPriceService finalOcAcPrice;
    @InjectMocks
    private PolicyService service;
    @Mock
    private PolicyResponseDto policyResponseDto;
    @Mock
    private Policy policy;
    @Mock
    private Client client;
    @Mock
    private Car car;


    @BeforeEach
    void setUp() {
        policy = new Policy(1L, "SDNKS82QEW", null, 2000, LocalDate.now(), LocalDate.now().plusYears(1));
        car = new Car();
        client = new Client();
    }


    @Test
    void Should_Find_Policy_By_Id() {
        given(policyRepository.findById(1L)).willReturn(Optional.of(policy));
        given(policyMapper.entityToDto(policy))
                .willReturn(policyResponseDto);
        assertThat(service.findPolicyById(1L)).isEqualTo(policyResponseDto);
    }

    @Test
    void should_save_oc_policy() {
        //Given
        car.setId(1L);
        client.setId(1L);
        LocalDate currentDate = LocalDate.now();
        given(stringGenerator.generateUniqueString()).willReturn("SDNKS82QEW");
        given(finalOcPrice.calculateOcInsurancePrice(1L, 1L)).willReturn(2000.0);
        //When
        Policy newPolicy = service.saveOCPolicy(client.getId(), car.getId());
        //Then
        assertThat(newPolicy).isNotNull();
        assertThat(newPolicy.getPolicyName()).isEqualTo(policy.getPolicyName());
        assertThat(newPolicy.getStartDate()).isEqualTo(currentDate);
        assertThat(newPolicy.getEndDate()).isEqualTo(currentDate.plusYears(1));
        assertThat(newPolicy.getPriceOfInsurance()).isEqualTo(2000.0);

    }

    @Test
    void should_save_oc_ac_policy() {
        //Given
        car.setId(1L);
        client.setId(1L);
        LocalDate currentDate = LocalDate.now();
        given(stringGenerator.generateUniqueString()).willReturn("SDNKS82QEW");
        given(finalOcAcPrice.finalAcOcInsurancePrice(1L, 1L)).willReturn(2000.0);
        //When
        Policy newPolicy = service.saveACAndOCPolicy(client.getId(), car.getId());
        //Then
        assertThat(newPolicy).isNotNull();
        assertThat(newPolicy.getPolicyName()).isEqualTo(policy.getPolicyName());
        assertThat(newPolicy.getStartDate()).isEqualTo(currentDate);
        assertThat(newPolicy.getEndDate()).isEqualTo(currentDate.plusYears(1));
        assertThat(newPolicy.getPriceOfInsurance()).isEqualTo(2000.0);

    }
}



