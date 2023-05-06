package com.carinsurance.insurancecalculator;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class UniqueStringGeneratorTest {
    private final UniqueStringGenerator generator = new UniqueStringGenerator();

    @Test
    @DisplayName("Should generate string with length 10")
    void generate_string() {
        //given & when
        String uniqueString = generator.generateUniqueString();
        //Then
        assertThat(uniqueString).hasSize(10);
    }

}
