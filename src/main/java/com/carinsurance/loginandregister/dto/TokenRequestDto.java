package com.carinsurance.loginandregister.dto;


import javax.validation.constraints.NotBlank;

public record TokenRequestDto(
        @NotBlank
        String username,
        @NotBlank
        String password) {

}


