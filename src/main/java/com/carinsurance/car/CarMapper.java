package com.carinsurance.car;

import com.carinsurance.car.dto.CarRequestDto;
import com.carinsurance.car.dto.CarResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CarMapper {


    CarMapper carMapper = Mappers.getMapper(CarMapper.class);


    CarResponseDto entityToDto(Car car);

    Car dtoToEntity(CarRequestDto carRequestDto);




}
