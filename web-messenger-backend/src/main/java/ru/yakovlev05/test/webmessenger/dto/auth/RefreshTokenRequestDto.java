package ru.yakovlev05.test.webmessenger.dto.auth;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "Запрос для обновления access token")
@Data
public class RefreshTokenRequestDto {

    @Schema(description = "refresh token", example = "eyJhbGciOiJIUzI1...")
    private String refreshToken;
}
