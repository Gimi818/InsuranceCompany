package com.carinsurance.car;

import com.carinsurance.car.dto.CarRequestDto;
import com.carinsurance.car.dto.CarResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cars")
public class CarController {

    private final CarService carService;

    @PostMapping("/add")
    public ResponseEntity<Car> saveCar(@RequestBody CarRequestDto carRequestDto) {
        return new ResponseEntity<>(carService.saveCar(carRequestDto), HttpStatus.CREATED);
    }


    @GetMapping("/{id}")
    public ResponseEntity<CarResponseDto> findCarById(@PathVariable Long id) {
        CarResponseDto carResponseDto = carService.findCarById(id);
        return ResponseEntity.status(HttpStatus.OK).body(carResponseDto);

    }

    @PutMapping("/{carId}/policy/{policyId}")
    public ResponseEntity<Void> addPolicyToCar(@PathVariable Long carId, @PathVariable Long policyId) {
        carService.assignPolicyToCar(carId, policyId);
        return ResponseEntity.ok().build();
    }

}
