package ru.yakovlev05.test.webmessenger.dto.message;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.yakovlev05.test.webmessenger.dto.user.UserDto;

import java.util.Date;

@Schema(description = "Модель сообщения с информацией")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MessageResponseDto {
    @Schema(description = "Id сообщения", example = "1")
    private Long id;

    @Schema(description = "Текст сообщения",  example = "Привет!")
    private String message;

    @Schema(description = "Дата отправки", example = "2024-08-19T08:13:34.633+00:00")
    private Date published;

    @Schema(description = "Пользователь, который отправил")
    private UserDto sender;
}