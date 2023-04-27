package com.carinsurance.policy;

import com.carinsurance.car.CarRepository;
import com.carinsurance.policy.dto.PolicyRequestDto;
import com.carinsurance.policy.dto.PolicyResponseDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

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
    private PolicyRequestDto policyRequestDto;
    private PolicyResponseDto policyResponseDto;
    private Policy policy;

    @BeforeEach
    void setUp() {
        policyRequestDto = new PolicyRequestDto(1000, LocalDate.now(), LocalDate.now().plusYears(1));
        policy = new Policy(1L, "SDNKS82QEW", 1000, LocalDate.now(), LocalDate.now().plusYears(1));
    }

}
