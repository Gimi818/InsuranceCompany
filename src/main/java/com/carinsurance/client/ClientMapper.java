package com.carinsurance.client;

import com.carinsurance.client.dto.ClientRequestDto;
import com.carinsurance.client.dto.ClientResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface ClientMapper {

    ClientMapper clientMapper = Mappers.getMapper(ClientMapper.class);


    ClientResponseDto entityToDto(Client client);

    Client dtoToEntity(ClientRequestDto clientRequestDto);



}
