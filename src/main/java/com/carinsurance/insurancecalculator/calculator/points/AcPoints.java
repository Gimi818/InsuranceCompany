package com.carinsurance.insurancecalculator.calculator.points;

import com.carinsurance.car.Car;
import com.carinsurance.car.enums.CarModel;
import com.carinsurance.car.enums.ParkingType;
import com.carinsurance.client.Client;
import com.carinsurance.insurancecalculator.Calculator;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import static com.carinsurance.insurancecalculator.FinalNumbers.*;

@Component
@AllArgsConstructor
@Log4j2
public class AcPoints implements Calculator {
    @Override
    public double pointsForClientAge(Client client) {

        if (client.getAge() < LOWER_AGE_LIMIT) {
            return 0.020;
        } else if (client.getAge() <= MIDDLE_AGE_LIMIT) {
            return 0.003;
        } else {
            return 0.020;
        }

    }

    @Override
    public double pointsForEnginCapacity(Car car) {
        if (car.getEnginCapacity() <= LOWER_ENGINE_CAPACITY_LIMIT) {
            return 0.002;
        } else if (car.getEnginCapacity() <= MIDDLE_ENGINE_CAPACITY_LIMIT) {
            return 0.005;
        } else if (car.getEnginCapacity() <= HIGHER_ENGINE_CAPACITY_LIMIT) {
            return 0.007;
        } else {
            return 0.012;
        }

    }

    @Override
    public double pointsForTypeOfVehicle(Car car) {

        if (car.getCarModel() == CarModel.CAR) {
            return 0.001;
        } else if (car.getCarModel() == CarModel.LORRY) {
            return 0.03;
        }
        throw new IllegalArgumentException("Unknown vehicle type : " + car.getCarModel());

    }

    @Override
    public double pointsForParkingType(Car car) {
        if (car.getParkingType() == ParkingType.GARAGE) {
            return 0.005;
        } else if (car.getParkingType() == ParkingType.DRIVEWAY) {
            return 0.011;
        } else if (car.getParkingType() == ParkingType.ROAD) {
            return 0.02;
        }
        throw new IllegalArgumentException("Unknown parking type : " + car.getParkingType());
    }

    @Override
    public double pointsForVehicleAge(Car car) {

        if (car.getYearOfManufacture() < HIGHER_LIMIT_YEAR_OF_MANUFACTURE) {
            return 0.012;
        } else if (car.getYearOfManufacture() < MIDDLE_LIMIT_YEAR_OF_MANUFACTURE) {
            return 0.009;
        } else if (car.getYearOfManufacture() <= LOWER_LIMIT_YEAR_OF_MANUFACTURE) {
            return 0.01;
        }
        return 0.001;
    }

    @Override
    public double pointsForAverageKMTraveledPerYear(Car car) {

        if (car.getAverageKmTraveledPerYear() < LOWER_LIMIT_KM_PER_YEAR) {
            return 0.003;
        } else if (car.getAverageKmTraveledPerYear() <= MIDDLE_LIMIT_KM_PER_YEAR) {
            return 0.006;
        } else {
            return 0.001;
        }

    }
}
