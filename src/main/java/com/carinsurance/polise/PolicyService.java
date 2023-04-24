package com.carinsurance.polise;

import com.carinsurance.client.Client;
import com.carinsurance.client.dto.ClientRequestDto;
import com.carinsurance.client.dto.ClientResponseDto;
import com.carinsurance.client.exception.ClientNotFoundException;
import com.carinsurance.polise.dto.PolicyRequestDto;
import com.carinsurance.polise.dto.PolicyResponseDto;
import com.carinsurance.polise.exception.PolicyNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import static com.carinsurance.client.ClientMapper.clientMapper;
import static com.carinsurance.polise.PolicyMapper.policyMapper;

@Service
@AllArgsConstructor
public class PolicyService {

    private final PolicyRepository policyRepository;

    public Policy savePolicy(PolicyRequestDto policyRequestDto) {
        Policy newPolicy = policyRepository.save(policyMapper.dtoToEntity(policyRequestDto));
        return newPolicy;
    }

    public PolicyResponseDto findPolicyById(Long id) {
        Policy policy = policyRepository.findById(id).orElseThrow(() -> new PolicyNotFoundException(id));
        return policyMapper.entityToDto(policy);
    }


}
