package com.carinsurance.policy;


import com.carinsurance.policy.dto.PolicyResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/AC")
public class PolicyController {
    private final PolicyService policyService;

    @PostMapping("/{clientId}/cars/{carId}")
    public ResponseEntity<Policy> saveAcInsurance(@PathVariable Long clientId, @PathVariable Long carId) {

        return new ResponseEntity<>(policyService.saveACPolicy(clientId, carId), HttpStatus.CREATED);
    }

    @PostMapping("/OC/{clientId}/cars/{carId}")
    public ResponseEntity<Policy> saveAcAndOcInsurance(@PathVariable Long clientId, @PathVariable Long carId) {

        return new ResponseEntity<>(policyService.saveACAndOCPolicy(clientId, carId), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PolicyResponseDto> findPolicyById(@PathVariable Long id) {
        PolicyResponseDto policyResponseDto = policyService.findPolicyById(id);
        return ResponseEntity.status(HttpStatus.OK).body(policyResponseDto);
    }

}
