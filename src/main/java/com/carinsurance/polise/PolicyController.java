package com.carinsurance.polise;

import com.carinsurance.car.Car;
import com.carinsurance.car.CarService;
import com.carinsurance.car.dto.CarRequestDto;
import com.carinsurance.car.dto.CarResponseDto;
import com.carinsurance.polise.dto.PolicyRequestDto;
import com.carinsurance.polise.dto.PolicyResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/policy")
public class PolicyController {
    private final PolicyService policyService;

    @PostMapping
    public ResponseEntity<Policy> savePolicy(@RequestBody PolicyRequestDto policyRequestDto) {
        return new ResponseEntity<>(policyService.savePolicy(policyRequestDto), HttpStatus.CREATED);
    }


    @GetMapping("/{id}")
    public ResponseEntity<PolicyResponseDto> findPolicyById(@PathVariable Long id) {
        PolicyResponseDto policyResponseDto = policyService.findPolicyById(id);
        return ResponseEntity.status(HttpStatus.OK).body(policyResponseDto);
    }

}
