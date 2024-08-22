package ru.yakovlev05.test.webmessenger.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Schema(description = "Модель для сообщений от сервера / ошибок")
@Data
@AllArgsConstructor
public class MessageDto {
    private String message;
}
