package ru.yakovlev05.test.webmessenger.dto.auth;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Schema(description = "Ответ с токенами для авторизации")
@Data
@AllArgsConstructor
public class JwtAuthResponseDto {
    @Schema(description = "Access token (для защищённых эндпоинтов)", example = "eyJhbGciOiJIUzI1...")
    private String token;

    @Schema(description = "Токен для обновления access token", example = "eyJhbGciOiJIUzI1...")
    private String refreshToken;
}
