package com.carinsurance.car;

import com.carinsurance.car.dto.CarRequestDto;
import com.carinsurance.car.dto.CarResponseDto;
import com.carinsurance.car.exception.CarNotFoundException;
import com.carinsurance.policy.Policy;
import com.carinsurance.policy.PolicyRepository;
import com.carinsurance.policy.exception.PolicyNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.catchThrowable;
import static org.junit.Assert.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class CarServiceTest {
    @Mock
    private CarRepository carRepository;
    @Mock
    private CarMapper carMapper;
    @Mock
    private PolicyRepository policyRepository;

    @InjectMocks
    private CarService carService;
    @Mock
    private CarRequestDto carRequestDto;
    @Mock
    private CarResponseDto carResponseDto;
    @Mock
    private Car car;
    @Mock
    private Policy policy;

    @BeforeEach
    void setUp() {
        carRequestDto = new CarRequestDto("Bmw", "X5", 30000, null, null, 2010, 3.0, 21000);
        car = new Car(1L, "Bmw", "X5", 30000, null, null, 2010, 3.0, 21000, null);
    }

    @Test
    void should_save_car() {
        given(carRepository.save(carMapper.dtoToEntity(carRequestDto)))
                .willReturn(car);
        assertThat(carService.saveCar(carRequestDto))
                .isEqualTo(car);
    }

    @Test
    void should_find_car_by_id() {
        given(carRepository.findById(1L)).willReturn(Optional.of(car));
        given(carMapper.entityToDto(car))
                .willReturn(carResponseDto);

        assertThat(carService.findCarById(1L)).isEqualTo(carResponseDto);
    }

    @Test
    void should_assign_policy_to_car() {
        Policy policy = new Policy(1L, "X83DSHA2", null, 1000, LocalDate.now(), LocalDate.now().plusYears(1));
        given(carRepository.findById(1L)).willReturn(Optional.of(car));
        given(policyRepository.findById(1L)).willReturn(Optional.of(policy));
        given(carRepository.save(car)).willReturn(car);

        // when
        Car result = carService.assignPolicyToCar(1L, 1L);
        // then
        assertThat(result).isEqualTo(car);
        assertThat(result.getPolicy()).isEqualTo(policy);
        BDDMockito.verify(carRepository, Mockito.times(1)).save(car);


    }

    @Test
    @DisplayName("Should throw a car not found exception when passing a non-existent car id to add a policy to the car")
    void should_throw_car_not_found_exception() {
        // given
        Long nonExistingCarId = 100L;

        given(carRepository.findById(nonExistingCarId)).willReturn(Optional.empty());

        // when
        Throwable throwable = catchThrowable(() -> carService.assignPolicyToCar(nonExistingCarId, 1L));

        // then
        assertThat(throwable).isInstanceOf(CarNotFoundException.class);
        BDDMockito.verify(carRepository, Mockito.times(1)).findById(nonExistingCarId);
    }

    @Test
    @DisplayName("Should throw a policy not found exception when passing a non-existent policy id to add a policy to the car")
    void should_throw_policy_not_found_exception() {
        // given
        Long nonExistingPolicyId = 100L;

        given(carRepository.findById(1L)).willReturn(Optional.of(car));
        given(policyRepository.findById(nonExistingPolicyId)).willReturn(Optional.empty());

        // when
        Throwable throwable = catchThrowable(() -> carService.assignPolicyToCar(car.getId(), nonExistingPolicyId));

        // then
        assertThat(throwable).isInstanceOf(PolicyNotFoundException.class);
        BDDMockito.verify(carRepository, Mockito.times(1)).findById(1L);
        BDDMockito.verify(policyRepository, Mockito.times(1)).findById(nonExistingPolicyId);
    }

    @Test
    @DisplayName("Should throw CarNotFoundException when client is not found")
    void should_throw_CarNotFoundException() {
        // Given
        Long carId = 1L;
        //when
        when(carRepository.findById(carId)).thenReturn(Optional.empty());
        //Then
        assertThrows(CarNotFoundException.class, () -> carService.findCarById(carId));
    }
}
