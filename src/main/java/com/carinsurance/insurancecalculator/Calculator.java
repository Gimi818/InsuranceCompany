package com.carinsurance.insurancecalculator;

import com.carinsurance.car.Car;
import com.carinsurance.car.CarModel;
import com.carinsurance.client.Client;
import com.carinsurance.policy.Policy;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;

import static com.carinsurance.insurancecalculator.FinalNumbers.*;

@Service
@AllArgsConstructor
@Data
public class Calculator {


    public double pointsForAge(Client client) {

        if (client.getAge() < LOWER_AGE_LIMIT) {
            return 0.005;
        } else if (client.getAge() <= MIDDLE_AGE_LIMIT) {
            return 0;
        } else {
            return 0.007;
        }

    }

    public double pointsForEnginCapacity(Car car) {

        if (car.getEnginCapacity() <= LOWER_ENGINE_CAPACITY_LIMIT) {
            return 0.001;
        } else if (car.getEnginCapacity() <= MIDDLE_ENGINE_CAPACITY_LIMIT) {
            return 0.003;
        } else if (car.getEnginCapacity() <= HIGHER_ENGINE_CAPACITY_LIMIT) {
            return 0.005;
        } else {
            return 0.01;
        }


    }

    public double pointsForTypeOfVehicle(CarModel carModel) {

        if (carModel == CarModel.CAR) {
            return 0.002;
        } else if (carModel == CarModel.LORRY) {
            return 0.01;
        } else
            return 0;

    }

    public double pointsForVehicleAge(Car car) {

        if (car.getYearOfManufacture() > LOWER_LIMIT_YEAR_OF_MANUFACTURE) {
            return 0.001;
        } else if (car.getYearOfManufacture() >= MIDDLE_LIMIT_YEAR_OF_MANUFACTURE) {
            return 0.003;
        } else if (car.getYearOfManufacture() >= HIGHER_LIMIT_YEAR_OF_MANUFACTURE) {
            return 0.005;
        } else {
            return 0.01;
        }

    }

    public double pointsForAverageKMTraveledPerYear(Car car) {

        if (car.getAverageKmTraveledPerYear() < LOWER_LIMIT_KM_PER_YEAR) {
            return 0.001;
        } else if (car.getAverageKmTraveledPerYear() <= MIDDLE_LIMIT_KM_PER_YEAR) {
            return 0.002;
        } else {
            return 0.006;
        }

    }

    public double calculatePrice(Car car, Client client) {
        double points = pointsForAge(client) + pointsForVehicleAge(car) + pointsForEnginCapacity(car)
                + pointsForAverageKMTraveledPerYear(car) + pointsForVehicleAge(car);
        double price = points * car.getCarValue();
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        return Double.parseDouble(decimalFormat.format(price));
    }
}
