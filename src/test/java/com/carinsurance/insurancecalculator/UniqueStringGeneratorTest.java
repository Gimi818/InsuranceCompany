package com.carinsurance.insurancecalculator;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class UniqueStringGeneratorTest {
    private final UniqueStringGenerator generator = new UniqueStringGenerator();

    @Test
    @DisplayName("Should generate string with length 10")
    void generate_string() {
        String uniqueString = generator.generateUniqueString();
        assertThat(uniqueString).hasSize(10);
    }

}
