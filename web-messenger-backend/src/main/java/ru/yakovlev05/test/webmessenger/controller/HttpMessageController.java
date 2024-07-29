package ru.yakovlev05.test.webmessenger.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.yakovlev05.test.webmessenger.dto.message.MessageResponseDto;
import ru.yakovlev05.test.webmessenger.service.message.MessageService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/messages")
@RequiredArgsConstructor
public class HttpMessageController {
    private final MessageService messageService;

    @GetMapping
    public List<MessageResponseDto> getMessages(@RequestParam int page, @RequestParam int size) {
        return messageService.getMessages(page, size);
    }
}
