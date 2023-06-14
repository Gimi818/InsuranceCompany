package com.carinsurance.policy;

import com.carinsurance.insurancecalculator.acoc.CalculateFinalAcOcPriceService;
import com.carinsurance.insurancecalculator.oc.CalculateFinalOCPriceService;
import com.carinsurance.insurancecalculator.UniqueStringGenerator;
import com.carinsurance.policy.dto.PolicyResponseDto;
import com.carinsurance.policy.exception.PolicyNotFoundException;
import lombok.AllArgsConstructor;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.time.LocalDate;


@Service
@AllArgsConstructor
@Log4j2
public class PolicyService {

    private final PolicyRepository policyRepository;
    private final PolicyMapper policyMapper;
    private final UniqueStringGenerator generator;
    private final CalculateFinalOCPriceService price;
    private final CalculateFinalAcOcPriceService calculateFinalPrice;


    public Policy saveOCPolicy(Long clientId, Long carId) {
        log.info("Creating new OC policy for client with ID {} and car with ID {}", clientId, carId);
        Policy newPolicy = Policy.builder()
                .policyName(generator.generateUniqueString())
                .policyType(PolicyType.OC)
                .startDate(LocalDate.now())
                .endDate(LocalDate.now().plusYears(1))
                .priceOfInsurance(price.calculateOcInsurancePrice(clientId, carId))
                .build();
        policyRepository.save(newPolicy);
        log.info("Created OC new policy {}", newPolicy);
        return newPolicy;
    }

    public Policy saveACAndOCPolicy(Long clientId, Long carId) {
        log.info("Creating new OC/AC policy for client with ID {} and car with ID {}", clientId, carId);

        Policy newPolicy = Policy.builder()
                .policyName(generator.generateUniqueString())
                .policyType(PolicyType.OCAC)
                .startDate(LocalDate.now())
                .endDate(LocalDate.now().plusYears(1))
                .priceOfInsurance(calculateFinalPrice.finalAcOcInsurancePrice(clientId, carId))
                .build();
        policyRepository.save(newPolicy);
        log.info("Created new OC/AC policy {}", newPolicy);
        return newPolicy;

    }


    public PolicyResponseDto findPolicyById(Long id) {
        log.info("Finding policy with ID {}", id);
        Policy policy = policyRepository.findById(id).orElseThrow(() -> new PolicyNotFoundException(id));
        log.info("Found policy {}", policy);
        return policyMapper.entityToDto(policy);
    }


}

