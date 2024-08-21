package ru.yakovlev05.test.webmessenger.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.yakovlev05.test.webmessenger.dto.auth.JwtAuthResponseDto;
import ru.yakovlev05.test.webmessenger.dto.auth.LoginRequestDto;
import ru.yakovlev05.test.webmessenger.dto.auth.RefreshTokenRequestDto;
import ru.yakovlev05.test.webmessenger.dto.auth.RegistrationRequestDto;
import ru.yakovlev05.test.webmessenger.service.auth.AuthService;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/registration")
    public JwtAuthResponseDto registration(@RequestBody RegistrationRequestDto request) {
        return authService.registration(request);
    }

    @PostMapping("/login")
    public JwtAuthResponseDto login(@RequestBody LoginRequestDto request) {
        return authService.login(request);
    }

    @PostMapping("/refresh")
    public JwtAuthResponseDto refreshToken(@RequestBody RefreshTokenRequestDto request) {
        return authService.refreshToken(request);
    }
}
