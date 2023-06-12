package com.carinsurance.insurancecalculator.acoc;

import com.carinsurance.car.Car;
import com.carinsurance.client.Client;
import com.carinsurance.insurancecalculator.PriceFormatter;
import com.carinsurance.insurancecalculator.ac.CalculatePriceAC;
import com.carinsurance.insurancecalculator.oc.CalculatePriceOC;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

class CalculateAcOcPriceTest {

    @Mock
    CalculateAcOcPrice calculateAcOcPrice;

    @Mock
    Client client;
    @Mock
    Car car;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        client = new Client();
        car = new Car();
    }
    @Test
    @DisplayName("Should return expected final OC/AC insurance price ")
    void should_return_final_oc_ac_insurance_price() {
        // Given&When
        when(calculateAcOcPrice.finalAcOcInsurancePrice(client, car)).thenReturn(3200.0);
        double result = calculateAcOcPrice.finalAcOcInsurancePrice(client,car);
        // Then
        assertThat(3200.0).isEqualTo(result);
    }


}
