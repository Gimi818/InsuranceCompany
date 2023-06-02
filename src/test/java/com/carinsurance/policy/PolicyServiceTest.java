package com.carinsurance.policy;

import com.carinsurance.car.Car;
import com.carinsurance.car.CarRepository;
import com.carinsurance.car.CarService;
import com.carinsurance.client.Client;
import com.carinsurance.client.ClientRepository;
import com.carinsurance.insurancecalculator.CalculatorOC;
import com.carinsurance.insurancecalculator.UniqueStringGenerator;
import com.carinsurance.policy.dto.PolicyRequestDto;
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
    private CarRepository carRepository;
    @Mock
    private ClientRepository clientRepository;
    @Mock
    private UniqueStringGenerator uniqueStringGenerator;
    @Mock
    private CalculatorOC calculator;

    @InjectMocks
    private PolicyService policyService;
    private CarService carService;
    private PolicyRequestDto policyRequestDto;
    private PolicyResponseDto policyResponseDto;

    private Policy policy;
    private Client client;
    private Car car;

    @BeforeEach
    void setUp() {
        policyRequestDto = new PolicyRequestDto(1000,"AC", LocalDate.now(), LocalDate.now().plusYears(1));
        policy = new Policy(1L, "SDNKS82QEW","AC", 1000, LocalDate.now(), LocalDate.now().plusYears(1));
        car = new Car(1L, "Bmw", "X5", 30000, null,null, 2010, 3.0, 21000, policy);
        client = new Client(1L, "John", "New", 30, null);
    }


    @Test
    void find_Policy_By_Id() {
        given(policyRepository.findById(1L)).willReturn(Optional.of(policy));
        given(policyMapper.entityToDto(policy))
                .willReturn(policyResponseDto);

        assertThat(policyService.findPolicyById(1L)).isEqualTo(policyResponseDto);
    }

//    @Test
//    void should_assign_policy_to_car() {
//        given(clientRepository.findById(client.getId())).willReturn(Optional.of(client));
//        given(carRepository.findById(car.getId())).willReturn(Optional.of(car));
//        given(uniqueStringGenerator.generateUniqueString()).willReturn("SDNKS82QEW");
//        given(calculator.calculatePrice(car, client)).willReturn(1000.0);
//        Policy policy1 = policyService.savePolicy(client.getId(), car.getId());
//
//        assertThat(policy).isNotNull();
//        assertThat(policy.getPolicyName()).isEqualTo("SDNKS82QEW");
//        assertThat(policy.getStartDate()).isEqualTo(LocalDate.now());
//        assertThat(policy.getEndDate()).isEqualTo(LocalDate.now().plusYears(1));
//        assertThat(policy.getPriceOfInsurance()).isEqualTo(1000.0);
//
//        assertThat(policy1).isEqualTo(policy);
//    }
}
