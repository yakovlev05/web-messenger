package ru.yakovlev05.test.webmessenger.dto.auth;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(description = "Запрос на вход")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequestDto {
    @Schema(description = "Логин", example = "username")
    private String login;

    @Schema(description = "Пароль", example = "mypassword")
    private String password;
}
