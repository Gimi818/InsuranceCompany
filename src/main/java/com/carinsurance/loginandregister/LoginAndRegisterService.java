package com.carinsurance.loginandregister;

import com.carinsurance.loginandregister.dto.RegisterUserRequestDto;
import com.carinsurance.loginandregister.dto.RegistrationUserResponseDto;
import com.carinsurance.loginandregister.dto.UserDto;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class LoginAndRegisterService {
    private static final String USER_NOT_FOUND = "User not found";
    private final LoginRepository loginRepository;

    public RegistrationUserResponseDto register(RegisterUserRequestDto registerUserRequestDto) {
        final User user = User.builder()
                .username(registerUserRequestDto.username())
                .password(registerUserRequestDto.password())
                .build();
        User savedUser = loginRepository.save(user);
        return new RegistrationUserResponseDto(savedUser.getId(), true, savedUser.getUsername());
    }

    public UserDto findByUsername(String username) {
        return loginRepository.findByUsername(username)
                .map(user -> new UserDto(user.getId(), user.getUsername(), user.getPassword()))
                .orElseThrow(() -> new BadCredentialsException(USER_NOT_FOUND));
    }


}
