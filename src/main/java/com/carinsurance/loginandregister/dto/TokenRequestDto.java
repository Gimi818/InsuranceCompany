package com.carinsurance.loginandregister.dto;

import jakarta.validation.constraints.NotBlank;

public record TokenRequestDto(
        @NotBlank(message = "{password.not.blank}")
        String username,
        @NotBlank(message = "{password.not.blank}")
        String password) {

}


