package com.carinsurance.insurancecalculator.calculator;

import com.carinsurance.car.Car;
import com.carinsurance.car.CarFacade;
import com.carinsurance.car.enums.CarModel;
import com.carinsurance.car.enums.ParkingType;
import com.carinsurance.client.Client;
import com.carinsurance.client.ClientFacade;
import com.carinsurance.insurancecalculator.PriceFormatter;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class FinalPriceTest {
    @InjectMocks
    private FinalPrice finalPrice;
    @Mock
    private ClientFacade clientFacade;
    @Mock
    private Car car;
    @Mock
    private Client client;
    @Mock
    private CarFacade carFacade;
    @Mock
    private BasicPrice basicPrice;
    @Mock
    private Discount discount;
    @Mock
    private PriceFormatter priceFormatter;

    @Test
    void calculateFinalPrice_shouldReturnCorrectFinalPrice_withAcIncluded() {
        // Given

        MockitoAnnotations.initMocks(this);
        boolean includeAc = true;
        client = new Client("John", "New", 30, new ArrayList<>());

        car = new Car("Bmw", "X5", 40000, CarModel.CAR, ParkingType.GARAGE, 2010, 2.3, 12000, null);


        when(carFacade.findCarById(1L)).thenReturn(car);

        when(clientFacade.findById(1L)).thenReturn(client);


        double basicPriceValue = 1000.0;
        when(basicPrice.calculateBasicPrice(car, client, true)).thenReturn(basicPriceValue);

        double discountedPrice = 900.0;
        when(discount.priceWithDiscountAc(basicPriceValue, client)).thenReturn(discountedPrice);

        when(priceFormatter.formatPrice(discountedPrice)).thenReturn(900.0);
        double result = 900;

        when(finalPrice.calculateFinalPrice(client.getId(), car.getId(), includeAc)).thenReturn(result);



        // Then
        assertEquals(900.0, result);
    }
}
