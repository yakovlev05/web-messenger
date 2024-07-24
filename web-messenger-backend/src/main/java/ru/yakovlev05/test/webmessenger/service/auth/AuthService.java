package ru.yakovlev05.test.webmessenger.service.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.yakovlev05.test.webmessenger.dto.auth.JwtAuthResponseDto;
import ru.yakovlev05.test.webmessenger.dto.auth.LoginRequestDto;
import ru.yakovlev05.test.webmessenger.dto.auth.RegistrationRequestDto;
import ru.yakovlev05.test.webmessenger.entity.UserEntity;
import ru.yakovlev05.test.webmessenger.entity.enums.Role;
import ru.yakovlev05.test.webmessenger.service.user.UserService;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserService userService;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public JwtAuthResponseDto registration(RegistrationRequestDto registrationRequestDto) {
        var userEntity = UserEntity.builder()
                .email(registrationRequestDto.getEmail())
                .username(registrationRequestDto.getUsername())
                .password(passwordEncoder.encode(registrationRequestDto.getPassword()))
                .name(registrationRequestDto.getName())
                .surname(registrationRequestDto.getSurname())
                .roles(Set.of(Role.ROLE_USER))
                .build();

        userService.createUser(userEntity);
        var jwt = jwtService.generateToken(userEntity.getUsername(), 5000000);
        return new JwtAuthResponseDto(jwt);
    }

    public JwtAuthResponseDto login(LoginRequestDto loginRequestDto) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginRequestDto.getLogin(),
                loginRequestDto.getPassword()
        ));

        return new JwtAuthResponseDto(jwtService.generateToken(loginRequestDto.getLogin(), 5000000));
    }
}
