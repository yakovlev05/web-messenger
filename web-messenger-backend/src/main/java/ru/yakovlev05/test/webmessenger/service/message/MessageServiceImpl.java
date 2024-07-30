package ru.yakovlev05.test.webmessenger.service.message;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.yakovlev05.test.webmessenger.dao.MessageRepository;
import ru.yakovlev05.test.webmessenger.dao.UserRepository;
import ru.yakovlev05.test.webmessenger.dto.message.MessageResponseDto;
import ru.yakovlev05.test.webmessenger.dto.user.UserDto;
import ru.yakovlev05.test.webmessenger.entity.MessageEntity;
import ru.yakovlev05.test.webmessenger.exception.CustomException;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService {
    private final MessageRepository messageRepository;
    private final UserRepository userRepository;

    @Override
    public MessageResponseDto save(String message, String senderUsername) {
        var messageEntity = MessageEntity.builder()
                .message(message)
                .published(new Date())
                .sender(userRepository.findByUsername(senderUsername)
                        .orElseThrow(() -> new CustomException(String.format("User with username %s not found", senderUsername))))
                .build();

        messageRepository.save(messageEntity);

        return MessageResponseDto.builder()
                .id(messageEntity.getId())
                .message(messageEntity.getMessage())
                .published(messageEntity.getPublished())
                .sender(UserDto.builder()
                        .name(messageEntity.getSender().getName())
                        .surname(messageEntity.getSender().getSurname())
                        .username(messageEntity.getSender().getUsername())
                        .email(messageEntity.getSender().getEmail())
                        .build())
                .build();
    }

    @Override
    public List<MessageResponseDto> getMessages(int page, int size, long dateInMs) {
        return messageRepository.findAll()
                .stream()
                .filter(x -> x.getPublished().compareTo(new Date(dateInMs)) <= 0)
                .sorted((x1, x2) -> x2.getPublished().compareTo(x1.getPublished()))
                .skip((long) (page - 1) * size)
                .limit(size)
                .map(x -> MessageResponseDto.builder()
                        .id(x.getId())
                        .sender(
                                new UserDto(
                                        x.getSender().getName(),
                                        x.getSender().getSurname(),
                                        x.getSender().getUsername(),
                                        x.getSender().getEmail()
                                )
                        )
                        .published(x.getPublished())
                        .message(x.getMessage())
                        .build())
                .toList();
    }
}
