package com.carinsurance.insurancecalculator;

import com.carinsurance.car.Car;
import com.carinsurance.client.Client;



public interface Calculator {

    public double pointsForClientAge(Client client);

    public double pointsForEnginCapacity(Car car);

    public double pointsForTypeOfVehicle(Car car);

    public double pointsForParkingType(Car car);

    public double pointsForVehicleAge(Car car);


    public double pointsForAverageKMTraveledPerYear(Car car);


}
