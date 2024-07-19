package ru.yakovlev05.test.webmessenger.dto.auth;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "Ответ с токеном доступа")
public class JwtAuthenticationResponse {

    @Schema(description = "Токен доступа")
    private String token;
}
