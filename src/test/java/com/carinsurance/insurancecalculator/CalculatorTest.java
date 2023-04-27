package com.carinsurance.insurancecalculator;

import com.carinsurance.car.Car;
import com.carinsurance.car.CarModel;
import com.carinsurance.client.Client;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.assertj.core.api.Assertions.assertThat;

class CalculatorTest {
    @Mock
    Client client;
    Client secondClient;
    @Mock
    Car car;
    Car secondCar;
    @Mock
    CarModel carModel = CarModel.valueOf("CAR");
    private final Calculator calculator = new Calculator();

    @BeforeEach
    void setUp() {

        client = new Client(null, "test", "test", 22, null);

        secondClient = new Client(null, "test", "test", 28, null);

        car = new Car(1L, "BMW", "X5", 62000, carModel, 2016, 3.0, 16000, null);

        secondCar = new Car(2L, "Audi", "A5", 45000, carModel, 2012, 1.8, 32000, null);
    }

    @Test
    @DisplayName("Should return 0.035 points when age is less than lower age limit (26)")
    void points_for_client_age() {

        //given && when
        double points = calculator.pointsForAge(client);
        //then
        assertThat(points).isEqualTo(0.035);
    }


    @Test
    @DisplayName("Should return 0.001 points when age  is in the range 26 - 65")
    void points_for_second_client_age() {

        //given && when
        double points = calculator.pointsForAge(secondClient);
        //then
        assertThat(points).isEqualTo(0.001);
    }

    @Test
    @DisplayName("Should return 0.007 points when age is greater than 65")
    void points_for_client_age_test() {
        //given
        client.setAge(70);
        // when
        double points = calculator.pointsForAge(client);
        //then
        assertThat(points).isEqualTo(0.02);
    }


    @Test
    @DisplayName("Should return 0.005 points when engin capacity is in the range 2.5 - 3.5")
    void points_for_engin_capacity() {
        //given & when
        double points = calculator.pointsForEnginCapacity(car);
        //then
        assertThat(points).isEqualTo(0.005);
    }

    @Test
    @DisplayName("Should return 0.01 points when engin capacity is greater than 3.5")
    void points_for_large_engin_capacity() {
        //given
        car.setEnginCapacity(5.0);
        //when
        double points = calculator.pointsForEnginCapacity(car);
        //then
        assertThat(points).isEqualTo(0.01);
    }

    @Test
    @DisplayName("Should return 0.01 points when engin capacity is less than 1.6")
    void points_for_low_engin_capacity() {
        //given
        car.setEnginCapacity(1.2);
        //when
        double points = calculator.pointsForEnginCapacity(car);
        //then
        assertThat(points).isEqualTo(0.001);
    }

    @Test
    @DisplayName("Should return 0.003 potins when engin capacity is in the range 1.6 - 2.5 ")
    void points_for_second_engin_capacity() {
        //given & when
        double points = calculator.pointsForEnginCapacity(secondCar);
        //then
        assertThat(points).isEqualTo(0.003);
    }


    @Test
    @DisplayName("Should return 0.001 points when type of vehicle is CAR")
    void points_for_type_of_vehicle() {
        //given & when
        double points = calculator.pointsForTypeOfVehicle(car);
        //then
        assertThat(points).isEqualTo(0.001);
    }


    @Test
    @DisplayName("Should return 0.01 points when type of vehicle is LORRY")
    void points_for_type_of_vehicle_test() {
        //given
        CarModel lorry = CarModel.valueOf("LORRY");
        car.setCarmodel(lorry);
        //when
        double points = calculator.pointsForTypeOfVehicle(car);
        //then
        assertThat(points).isEqualTo(0.03);
    }

    @Test
    @DisplayName("Should return 0.002 points when vehicle age is in the range 2015 - 2020")
    void points_for_vehicle_age() {
        //given & when
        double points = calculator.pointsForVehicleAge(car);
        //then
        assertThat(points).isEqualTo(0.002);
    }


    @Test
    @DisplayName("Should return 0.004 points when vehicle age is in the range 2010 - 2015")
    void points_for_second_vehicle_age() {
        //given & when
        double points = calculator.pointsForVehicleAge(secondCar);
        //then
        assertThat(points).isEqualTo(0.004);
    }

    @Test
    @DisplayName("Should return 0.01 points when the year of manufacture is older than 2010")
    void points_for_old_vehicle_age() {
        //given
        car.setYearOfManufacture(2000);
        // when
        double points = calculator.pointsForVehicleAge(car);
        //then
        assertThat(points).isEqualTo(0.01);
    }

    @Test
    @DisplayName("Should return 0.001 points when the year of manufacture is younger than 2020")
    void points_for_young_vehicle_age() {
        //given
        car.setYearOfManufacture(2022);
        // when
        double points = calculator.pointsForVehicleAge(car);
        //then
        assertThat(points).isEqualTo(0.001);
    }


    @Test
    @DisplayName("Should return 0.002 points when average number of kilometres travelled per year is in the range 15000km - 30000km  ")
    void points_for_average_KM_traveled_per_year() {
        //given & when
        double points = calculator.pointsForAverageKMTraveledPerYear(car);
        //then
        assertThat(points).isEqualTo(0.002);
    }


    @Test
    @DisplayName("Should return 0.006 points when the average number of kilometres travelled per year is greater than 30000km  ")
    void points_for_average_KM_traveled_per_year_2() {
        //given & when
        double points = calculator.pointsForAverageKMTraveledPerYear(secondCar);
        //then
        assertThat(points).isEqualTo(0.006);
    }

    @Test
    @DisplayName("Should return 0.001 points when average number of kilometres travelled per year is less  that 15000km ")
    void points_for_low_average_KM_traveled_per_year() {
        //given
        car.setAverageKmTraveledPerYear(5000);
        //when
        double points = calculator.pointsForAverageKMTraveledPerYear(car);
        //then
        assertThat(points).isEqualTo(0.001);
    }

    @Test
    @DisplayName("Should return insurance price 2914.0 zł for car ")
    void calculate_price() {
        //given
        double price = calculator.calculatePrice(car, client);
        //when
        assertThat(price).isEqualTo(2914.0);
        //then
    }

    @Test
    @DisplayName("Should return insurance price 855.0 zł for second car ")
    void calculate_price_for_second_car() {
        //given & when
        double price = calculator.calculatePrice(secondCar, secondClient);
        //then
        assertThat(price).isEqualTo(855.0);

    }
}
