package com.carinsurance.loginandregister;

import com.carinsurance.loginandregister.dto.RegisterUserRequestDto;
import com.carinsurance.loginandregister.dto.RegistrationUserResponseDto;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static com.carinsurance.loginandregister.RegisterController.Routes.*;

@RestController
@AllArgsConstructor
public class RegisterController {

    private final LoginAndRegisterService loginService;

    private final PasswordEncoder bCryptPasswordEncoder;


    @PostMapping(REGISTRATION)
    public ResponseEntity<RegistrationUserResponseDto> register(@RequestBody RegisterUserRequestDto registerUserRequestDto) {
        String encodedPassword = bCryptPasswordEncoder.encode(registerUserRequestDto.password());
        RegistrationUserResponseDto registerResult = loginService.register(
                new RegisterUserRequestDto(registerUserRequestDto.username(), encodedPassword));
        return ResponseEntity.status(HttpStatus.CREATED).body(registerResult);
    }

    static final class Routes {
        static final String REGISTRATION = "/register";


    }

}
