package com.carinsurance.policy;

import com.carinsurance.car.Car;
import com.carinsurance.car.CarRepository;
import com.carinsurance.car.CarService;
import com.carinsurance.policy.dto.PolicyRequestDto;
import com.carinsurance.policy.dto.PolicyResponseDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class PolicyServiceTest {
    @Mock
    private PolicyRepository policyRepository;
    @Mock
    private PolicyMapper policyMapper;
    @Mock
    private CarRepository carRepository;

    @InjectMocks
    private PolicyService policyService;
    private CarService carService;
    private PolicyRequestDto policyRequestDto;
    private PolicyResponseDto policyResponseDto;
    private Policy policy;
    private Car car;

    @BeforeEach
    void setUp() {
        policyRequestDto = new PolicyRequestDto(1000, LocalDate.now(), LocalDate.now().plusYears(1));
        policy = new Policy(1L, "SDNKS82QEW", 1000, LocalDate.now(), LocalDate.now().plusYears(1));
        car = new Car(1L, "Bmw", "X5", 30000, null, 2010, 3.0, 21000, policy);

    }


    @Test
    void find_Policy_By_Id() {
        BDDMockito.given(policyRepository.findById(1L)).willReturn(Optional.of(policy));
        BDDMockito.given(policyMapper.entityToDto(policy))
                .willReturn(policyResponseDto);

        assertThat(policyService.findPolicyById(1L)).isEqualTo(policyResponseDto);
    }

    @Test
    void asdasd() {
        BDDMockito.given(policyRepository.findById(policy.getId())).willReturn(Optional.of(policy));
        BDDMockito.given(carRepository.findById(car.getId())).willReturn(Optional.of(car));


        Car result = carService.assignPolicyToCar(car.getId(), policy.getId());
        assertThat(result).isEqualTo(policy);
    }
}
