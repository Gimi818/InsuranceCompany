package com.carinsurance.loginandregister;

import static org.assertj.core.api.ThrowableAssert.catchThrowable;
import static org.junit.jupiter.api.Assertions.assertAll;


import com.carinsurance.loginandregister.dto.RegisterUserRequestDto;
import com.carinsurance.loginandregister.dto.RegistrationUserResponseDto;
import com.carinsurance.loginandregister.dto.UserDto;
import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.Test;
import org.springframework.security.authentication.BadCredentialsException;

import static org.assertj.core.api.Assertions.assertThat;


public class LoginAndRegisterServiceTest {

    LoginAndRegisterService service = new LoginAndRegisterService(new InMemoryLoginRepository());

    RegisterUserRequestDto registerUserDto = new RegisterUserRequestDto("user", "password");


    @Test
    public void should_register_user() {

        // when
        RegistrationUserResponseDto register = service.register(registerUserDto);

        // then
        assertAll(
                () -> assertThat(register.created()).isTrue(),
                () -> assertThat(register.username()).isEqualTo("user")

        );
    }

    @Test
    public void should_find_user_by_user_name() {
        // given
        RegistrationUserResponseDto register = service.register(registerUserDto);

        // when
        UserDto userByName = service.findByUserName(register.username());

        // then
        assertThat(userByName).isEqualTo(new UserDto(register.id(), "user", "password"));
    }

    @Test
    public void should_throw_exception_when_user_not_found() {
        // given
        String username = "User";

        // when
        Throwable thrown = catchThrowable(() -> service.findByUserName(username));

        // then
        AssertionsForClassTypes.assertThat(thrown)
                .isInstanceOf(BadCredentialsException.class)
                .hasMessage("User not found");
    }
}
