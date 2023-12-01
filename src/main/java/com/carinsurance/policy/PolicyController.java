package com.carinsurance.policy;


import com.carinsurance.policy.dto.PolicyResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.carinsurance.policy.PolicyController.Routes.*;

@RestController
@RequiredArgsConstructor
public class PolicyController {
    private final PolicyService policyService;

    @PostMapping(OC)
    public ResponseEntity<PolicyResponseDto> saveAcInsurance(@PathVariable Long clientId) {

        return new ResponseEntity<>(policyService.saveOCPolicy(clientId), HttpStatus.CREATED);
    }

    @PostMapping(OCAC)
    public ResponseEntity<PolicyResponseDto> saveAcAndOcInsurance(@PathVariable Long clientId) {

        return new ResponseEntity<>(policyService.saveACAndOCPolicy(clientId), HttpStatus.CREATED);
    }

    static final class Routes {
        static final String ROOT = "/policies";
        static final String OC = ROOT + "/OC/{clientId}";
        static final String OCAC = ROOT + "/OC/AC/{clientId}";


    }
}
