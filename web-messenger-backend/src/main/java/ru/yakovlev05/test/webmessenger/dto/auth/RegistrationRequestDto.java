package ru.yakovlev05.test.webmessenger.dto.auth;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(description = "Модель для регистрации")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegistrationRequestDto {
    @Schema(description = "Имя пользователя", example = "Александр")
    private String name;

    @Schema(description = "Фамилия пользователя", example = "Некрасов")
    private String surname;

    @Schema(description = "Логин", example = "sanya2003")
    private String username;

    @Schema(description = "Пароль", example = "strongpassword")
    private String password;

    @Schema(description = "Почта", example = "aleksandr.nekrasov2003@mail.ru")
    private String email;
}