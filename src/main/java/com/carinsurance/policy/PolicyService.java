package com.carinsurance.policy;

import com.carinsurance.car.CarFacade;
import com.carinsurance.insurancecalculator.calculator.FinalPrice;
import com.carinsurance.insurancecalculator.UniqueStringGenerator;
import com.carinsurance.car.Car;
import com.carinsurance.policy.dto.PolicyResponseDto;
import lombok.AllArgsConstructor;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.time.LocalDate;


@Service
@AllArgsConstructor
@Log4j2
public class PolicyService {
    private final CarFacade carFacade;
    private final PolicyRepository policyRepository;
    private final UniqueStringGenerator generator;
    private final FinalPrice price;
    private final PolicyMapper mapper;


    public PolicyResponseDto saveOCPolicy(Long clientId) {
        Car car = carFacade.findCarWithoutPolicyForClient(clientId);
        Policy newPolicy = Policy.builder()
                .policyName(generator.generateUniqueString())
                .policyType(PolicyType.OC)
                .startDate(LocalDate.now())
                .endDate(LocalDate.now().plusYears(1))
                .priceOfInsurance(price.calculateFinalPrice(clientId, car.getId(), false))
                .build();
        policyRepository.save(newPolicy);
        car.setPolicy(newPolicy);
        carFacade.saveCarWithAddedInsurance(car);
        log.info("Created OC new policy {}", newPolicy.getPolicyName());
        return mapper.entityToDto(newPolicy);
    }

    public PolicyResponseDto saveACAndOCPolicy(Long clientId) {
        Car car = carFacade.findCarWithoutPolicyForClient(clientId);
        Policy newPolicy = Policy.builder()
                .policyName(generator.generateUniqueString())
                .policyType(PolicyType.OCAC)
                .startDate(LocalDate.now())
                .endDate(LocalDate.now().plusYears(1))
                .priceOfInsurance(price.calculateFinalPrice(clientId, car.getId(), true))
                .build();
        policyRepository.save(newPolicy);
        car.setPolicy(newPolicy);
        carFacade.saveCarWithAddedInsurance(car);
        log.info("Created new OC/AC policy {}", newPolicy.getPolicyName());
        return mapper.entityToDto(newPolicy);

    }


}

