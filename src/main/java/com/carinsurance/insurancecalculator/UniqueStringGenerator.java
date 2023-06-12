package com.carinsurance.insurancecalculator;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

@AllArgsConstructor
@Component
public class UniqueStringGenerator {

    public String generateUniqueString() {
        try {
            SecureRandom random = SecureRandom.getInstance("SHA1PRNG");

            byte[] randomBytes = new byte[16];
            random.nextBytes(randomBytes);


            MessageDigest digest = MessageDigest.getInstance("SHA-1");
            byte[] hashBytes = digest.digest(randomBytes);


            String base64Hash = Base64.getEncoder().encodeToString(hashBytes);


            String uniqueString = base64Hash.replaceAll("[^a-zA-Z0-9]", "").substring(0, 10);
            return uniqueString;

        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error generating unique string", e);
        }
    }
}
