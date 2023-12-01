package com.carinsurance.car;

import com.carinsurance.car.dto.CarRequestDto;
import com.carinsurance.car.dto.CreatedCarDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;


@Mapper(componentModel = "spring")
@Component
public interface CarMapper {


    CarMapper carMapper = Mappers.getMapper(CarMapper.class);


    CreatedCarDto entityToDto(Car car);


    Car dtoToEntity(CarRequestDto carRequestDto);


}
