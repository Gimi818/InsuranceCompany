package com.carinsurance.loginandregister;

import com.carinsurance.loginandregister.dto.RegisterUserRequestDto;
import com.carinsurance.loginandregister.dto.RegistrationUserResponseDto;
import com.carinsurance.loginandregister.dto.UserDto;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Log4j2
public class LoginAndRegisterService {
    private static final String USER_NOT_FOUND = "User not found";
    private final LoginRepository loginRepository;

    public RegistrationUserResponseDto register(RegisterUserRequestDto registerUserRequestDto) {
        log.info("Registering new user with username {}", registerUserRequestDto.username());
        final User user = User.builder()
                .username(registerUserRequestDto.username())
                .password(registerUserRequestDto.password())
                .build();
        User savedUser = loginRepository.save(user);
        log.info("Registered new user with ID {}", savedUser.getId());
        return new RegistrationUserResponseDto(savedUser.getId(), true, savedUser.getUsername());
    }

    public UserDto findByUserName(String username) {
        log.info("Finding user with username {}", username);

        return loginRepository.findByUsername(username)
                .map(user -> {
                    log.info("User found with username {}", username);
                    return new UserDto(user.getId(), user.getUsername(), user.getPassword());
                })
                .orElseThrow(() -> new BadCredentialsException(USER_NOT_FOUND));
    }


}
