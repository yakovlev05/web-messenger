package ru.yakovlev05.test.webmessenger.service.message;

import ru.yakovlev05.test.webmessenger.dto.message.MessageResponseDto;

import java.util.List;

public interface MessageService {
    MessageResponseDto save(String message, String senderUsername);

    List<MessageResponseDto> getMessages(int page, int size);
}