package ru.yakovlev05.test.webmessenger.service.message;

import ru.yakovlev05.test.webmessenger.dto.message.MessageRequestDto;
import ru.yakovlev05.test.webmessenger.dto.message.MessageResponseDto;

public interface MessageService {
    MessageResponseDto save(String message, String senderUsername);
}