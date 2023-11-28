package com.carinsurance.policy;


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
    public ResponseEntity<Policy> saveAcInsurance(@PathVariable Long clientId, @PathVariable Long carId) {

        return new ResponseEntity<>(policyService.saveOCPolicy(clientId, carId), HttpStatus.CREATED);
    }

    @PostMapping(OCAC)
    public ResponseEntity<Policy> saveAcAndOcInsurance(@PathVariable Long clientId, @PathVariable Long carId) {

        return new ResponseEntity<>(policyService.saveACAndOCPolicy(clientId, carId), HttpStatus.CREATED);
    }

    static final class Routes {
        static final String ROOT = "/policies";
        static final String OC = ROOT + "/OC/{clientId}/{carId}";
        static final String OCAC = ROOT + "/OC/AC/{clientId}/{carId}";


    }
}
