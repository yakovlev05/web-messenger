package ru.yakovlev05.test.webmessenger.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import ru.yakovlev05.test.webmessenger.dto.message.MessageRequestDto;
import ru.yakovlev05.test.webmessenger.dto.message.MessageResponseDto;
import ru.yakovlev05.test.webmessenger.service.message.MessageService;

@Controller
@RequiredArgsConstructor
public class MessageController {
    private final MessageService messageService;

    @MessageMapping("/message")
    @SendTo("/topic/messages")
    public MessageResponseDto processMessage(MessageRequestDto request, @AuthenticationPrincipal UserDetails userDetails) {
        return messageService.save(request.getMessage(), userDetails.getUsername());
    }
}

// Как это всё тестировать в Postman?
// Вопрос интересный: https://dev.to/danielsc/testing-stomp-websocket-with-postman-218a
// Коротка STOMP frames + null + base64 encode

//Сначала CONNECT, потом SUBSCRIBE, если хотим подписаться
//CONNECT
//accept-version:1.2,1.1,1.0
//heart-beat:10000,10000
//
//SUBSCRIBE
//id:sub-0
//destination:/topic/messages
//
//!!НЕ забываем про NULL в конце
//https://stomp.github.io/stomp-specification-1.2.html
