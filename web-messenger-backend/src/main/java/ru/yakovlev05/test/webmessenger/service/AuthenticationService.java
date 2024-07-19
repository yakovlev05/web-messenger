package ru.yakovlev05.test.webmessenger.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.yakovlev05.test.webmessenger.dto.auth.JwtAuthenticationResponse;
import ru.yakovlev05.test.webmessenger.dto.auth.SignInRequest;
import ru.yakovlev05.test.webmessenger.dto.auth.SignUpRequest;
import ru.yakovlev05.test.webmessenger.entity.Role;
import ru.yakovlev05.test.webmessenger.entity.UserEntity;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserService userService;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public JwtAuthenticationResponse signUp(SignUpRequest request) {

        var user = UserEntity.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.ROLE_USER)
                .build();

//        userService.create(user);
        System.out.println("============ТИПА СОЗДАЛИ ПОЛЬЗОВАТЕЛЯ");
        //todo: сделать метод создания

        var jwt = jwtService.generateToken(user);
        return new JwtAuthenticationResponse(jwt);
    }

    public JwtAuthenticationResponse signIn(SignInRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                request.getUsername(),
                request.getPassword()
        ));

        var user = userService
                .userDetailsService()
                .loadUserByUsername(request.getUsername());

        var jwt = jwtService.generateToken(user);
        return new JwtAuthenticationResponse(jwt);
    }
}
