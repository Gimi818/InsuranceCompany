package com.carinsurance.policy;

import com.carinsurance.policy.dto.PolicyRequestDto;
import com.carinsurance.policy.dto.PolicyResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/policies")
public class PolicyController {
    private final PolicyService policyService;

    @PostMapping("/{clientId}/cars/{carId}")
    public ResponseEntity<Policy> savePolicy( @PathVariable Long clientId, @PathVariable Long carId) {

        return new ResponseEntity<>(policyService.savePolicy( clientId, carId), HttpStatus.CREATED);
    }


    @GetMapping("/{id}")
    public ResponseEntity<PolicyResponseDto> findPolicyById(@PathVariable Long id) {
        PolicyResponseDto policyResponseDto = policyService.findPolicyById(id);
        return ResponseEntity.status(HttpStatus.OK).body(policyResponseDto);
    }

}
