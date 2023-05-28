package com.carinsurance.insurancecalculator;

import com.carinsurance.car.Car;
import com.carinsurance.car.CarModel;
import com.carinsurance.client.Client;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;

import static com.carinsurance.insurancecalculator.FinalNumbers.*;

@Component
@AllArgsConstructor
@Getter
@Log4j2
public class Calculator {

    private final DecimalFormat decimalFormat = new DecimalFormat("#.##");


    public double pointsForClientAge(Client client) {

        if (client.getAge() < LOWER_AGE_LIMIT) {
            return 0.035;
        } else if (client.getAge() <= MIDDLE_AGE_LIMIT) {
            return 0.001;
        } else {
            return 0.02;
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

    public double pointsForTypeOfVehicle(Car car) {

        if (car.getCarmodel() == CarModel.CAR) {
            return 0.001;
        } else if (car.getCarmodel() == CarModel.LORRY) {
            return 0.03;
        } else
            return 0;

    }

    public double pointsForVehicleAge(Car car) {

        if (car.getYearOfManufacture() < HIGHER_LIMIT_YEAR_OF_MANUFACTURE) {
            return 0.01;
        } else if (car.getYearOfManufacture() < MIDDLE_LIMIT_YEAR_OF_MANUFACTURE) {
            return 0.004;
        } else if (car.getYearOfManufacture() <= LOWER_LIMIT_YEAR_OF_MANUFACTURE) {
            return 0.002;
        }
        return 0.001;
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

    public double calculatePoints(Car car, Client client) {
        log.info("Calculating points for car with ID {} and client with ID {}", car.getId(), client.getId());
        double points = 0;
         points = pointsForClientAge(client)
                + pointsForVehicleAge(car)
                + pointsForEnginCapacity(car)
                + pointsForAverageKMTraveledPerYear(car)
                + pointsForVehicleAge(car)
                + pointsForTypeOfVehicle(car);
        log.info("Calculated points is: {}", points);
        return points;
    }

    public double calculatePrice(Car car , Client client){
        log.info("Calculating insurance price for car with ID {} and client with ID {}", car.getId(), client.getId());
        double price = calculatePoints(car,client) * car.getCarValue();
        log.info("Calculated insurance price is: {}", price);
        return Double.parseDouble(decimalFormat.format(price));
    }


}
