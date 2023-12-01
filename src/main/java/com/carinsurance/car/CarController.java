package com.carinsurance.car;

import com.carinsurance.car.dto.CarRequestDto;

import com.carinsurance.car.dto.CreatedCarDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.carinsurance.car.CarController.Routes.*;


@RestController
@RequiredArgsConstructor

public class CarController {

    private final CarService carService;

    @PostMapping(ROOT)
    public ResponseEntity<CreatedCarDto> saveCar(@RequestBody CarRequestDto car, @PathVariable Long clientId) {
        return new ResponseEntity<>(carService.saveCar(car, clientId), HttpStatus.CREATED);
    }


    static final class Routes {
        static final String ROOT = "/cars/{clientId}";


    }

}
