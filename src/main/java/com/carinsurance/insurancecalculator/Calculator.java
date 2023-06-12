package com.carinsurance.insurancecalculator;

import com.carinsurance.car.Car;
import com.carinsurance.client.Client;


public interface Calculator {

    double pointsForClientAge(Client client);

    double pointsForEnginCapacity(Car car);

    double pointsForTypeOfVehicle(Car car);

    double pointsForParkingType(Car car);

    double pointsForVehicleAge(Car car);


    double pointsForAverageKMTraveledPerYear(Car car);


}
