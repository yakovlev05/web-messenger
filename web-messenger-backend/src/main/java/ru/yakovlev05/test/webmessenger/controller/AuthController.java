package ru.yakovlev05.test.webmessenger.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.yakovlev05.test.webmessenger.dto.auth.JwtAuthResponseDto;
import ru.yakovlev05.test.webmessenger.dto.auth.LoginRequestDto;
import ru.yakovlev05.test.webmessenger.dto.auth.RegistrationRequestDto;
import ru.yakovlev05.test.webmessenger.service.auth.AuthService;
import ru.yakovlev05.test.webmessenger.service.user.UserService;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;
    private final AuthService authService;

    @PostMapping("/registration")
    public JwtAuthResponseDto registration(@RequestBody RegistrationRequestDto request) {
        return authService.registration(request);
    }

    @PostMapping("/login")
    public JwtAuthResponseDto login(@RequestBody LoginRequestDto request) {
        return authService.login(request);
    }

    @GetMapping("/secured")
    private String secured() {
        return "secured";
    }
}
