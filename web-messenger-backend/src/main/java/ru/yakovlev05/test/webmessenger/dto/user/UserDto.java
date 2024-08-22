package ru.yakovlev05.test.webmessenger.dto.user;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(description = "Модель с информацией о пользователе")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {
    @Schema(description = "Имя пользователя", example = "Александр")
    private String name;

    @Schema(description = "Фамилия пользователя", example = "Некрасов")
    private String surname;

    @Schema(description = "Логин пользователя", example = "sanya2003")
    private String username;

    @Schema(description = "Почта пользователя", example = "aleksandr.nekrasov2003@mail.ru")
    private String email;
}