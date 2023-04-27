package com.carinsurance.policy;

import com.carinsurance.policy.dto.PolicyRequestDto;
import com.carinsurance.policy.dto.PolicyResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface PolicyMapper {

    PolicyMapper policyMapper = Mappers.getMapper(PolicyMapper.class);


    PolicyResponseDto entityToDto(Policy policy);

}



