package com.carinsurance.polise;

import com.carinsurance.client.Client;
import com.carinsurance.client.dto.ClientRequestDto;
import com.carinsurance.client.dto.ClientResponseDto;
import com.carinsurance.polise.dto.PolicyRequestDto;
import com.carinsurance.polise.dto.PolicyResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface PolicyMapper {

    PolicyMapper policyMapper = Mappers.getMapper(PolicyMapper.class);


    PolicyResponseDto entityToDto(Policy policy);

    Policy dtoToEntity(PolicyRequestDto policyRequestDto);
}



