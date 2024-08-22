package ru.yakovlev05.test.webmessenger.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.yakovlev05.test.webmessenger.dto.message.MessageResponseDto;
import ru.yakovlev05.test.webmessenger.service.message.MessageService;

import java.util.List;

@Tag(name = "Message", description = "Взаимодействия с сообщениями")
@RestController
@RequestMapping("/api/v1/messages")
@RequiredArgsConstructor
public class HttpMessageController {
    private final MessageService messageService;

    /**
     * Поиск сообщений (пагинация)
     *
     * @param page номер страницы
     * @param size количество на страницу
     * @param dateInMs дату, с которой начинается поиск (то есть сообщения, опубликованные позже не учитываются)
     * @return список сообщений
     */
    @Operation(summary = "Получение всех сообщений (пагинация)")
    @GetMapping
    public List<MessageResponseDto> getMessages(@RequestParam int page, @RequestParam int size, @RequestParam long dateInMs) {
        return messageService.getMessages(page, size, dateInMs);
    }
}
