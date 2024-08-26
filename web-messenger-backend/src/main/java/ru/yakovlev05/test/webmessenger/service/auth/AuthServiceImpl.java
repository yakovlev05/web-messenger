package ru.yakovlev05.test.webmessenger.service.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.yakovlev05.test.webmessenger.dao.UserRepository;
import ru.yakovlev05.test.webmessenger.dto.auth.JwtAuthResponseDto;
import ru.yakovlev05.test.webmessenger.dto.auth.LoginRequestDto;
import ru.yakovlev05.test.webmessenger.dto.auth.RefreshTokenRequestDto;
import ru.yakovlev05.test.webmessenger.dto.auth.RegistrationRequestDto;
import ru.yakovlev05.test.webmessenger.entity.UserEntity;
import ru.yakovlev05.test.webmessenger.entity.enums.Role;
import ru.yakovlev05.test.webmessenger.exception.UnauthorizedException;
import ru.yakovlev05.test.webmessenger.mapper.UserMapper;
import ru.yakovlev05.test.webmessenger.service.user.UserService;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserService userService;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public JwtAuthResponseDto registration(RegistrationRequestDto registrationRequestDto) {
        UserEntity userEntity = userMapper.toUserEntity(
                registrationRequestDto,
                passwordEncoder.encode(registrationRequestDto.getPassword()),
                Set.of(Role.ROLE_USER)
        );

        userService.createUser(userEntity);
        var jwt = jwtService.generateToken(userEntity.getUsername());
        var refreshToken = jwtService.generateRefreshToken(userEntity.getUsername());
        return new JwtAuthResponseDto(jwt, refreshToken);
    }

    @Override
    public JwtAuthResponseDto login(LoginRequestDto loginRequestDto) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginRequestDto.getLogin(),
                loginRequestDto.getPassword()
        ));

        return new JwtAuthResponseDto(
                jwtService.generateToken(loginRequestDto.getLogin()),
                jwtService.generateRefreshToken(loginRequestDto.getLogin())
        );
    }

    @Override
    public JwtAuthResponseDto refreshToken(RefreshTokenRequestDto refreshTokenRequestDto) {
        if (!jwtService.validateToken(refreshTokenRequestDto.getRefreshToken())) {
            throw new UnauthorizedException("Invalid refresh token");
        }

        var username = jwtService.extractUsername(refreshTokenRequestDto.getRefreshToken());
        if (!userRepository.existsByUsername(username)) {
            throw new UnauthorizedException("User not found");
        }

        return new JwtAuthResponseDto(
                jwtService.generateToken(username),
                jwtService.generateRefreshToken(username)
        );
    }
}
