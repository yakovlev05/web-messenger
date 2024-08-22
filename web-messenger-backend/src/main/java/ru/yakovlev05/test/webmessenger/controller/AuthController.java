package ru.yakovlev05.test.webmessenger.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.yakovlev05.test.webmessenger.dto.auth.JwtAuthResponseDto;
import ru.yakovlev05.test.webmessenger.dto.auth.LoginRequestDto;
import ru.yakovlev05.test.webmessenger.dto.auth.RefreshTokenRequestDto;
import ru.yakovlev05.test.webmessenger.dto.auth.RegistrationRequestDto;
import ru.yakovlev05.test.webmessenger.service.auth.AuthService;

@Tag(name = "Auth", description = "Регистрация, авторизация и обновление токена")
@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @Operation(
            summary = "Регистрация пользователя",
            description = "Создание нового пользователя, в ответ отправляется access token + refresh token")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    content = @Content(schema = @Schema(implementation = JwtAuthResponseDto.class), mediaType = "application/json"))
    })
    @PostMapping("/registration")
    public JwtAuthResponseDto registration(@RequestBody RegistrationRequestDto request) {
        return authService.registration(request);
    }

    @Operation(summary = "Авторизация пользователя")
    @PostMapping("/login")
    public JwtAuthResponseDto login(@RequestBody LoginRequestDto request) {
        return authService.login(request);
    }

    @Operation(summary = "Обновление токена")
    @PostMapping("/refresh")
    public JwtAuthResponseDto refreshToken(@RequestBody RefreshTokenRequestDto request) {
        return authService.refreshToken(request);
    }
}
