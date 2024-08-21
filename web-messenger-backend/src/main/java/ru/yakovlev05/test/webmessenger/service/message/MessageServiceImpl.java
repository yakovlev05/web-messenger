package ru.yakovlev05.test.webmessenger.service.message;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.yakovlev05.test.webmessenger.dao.MessageRepository;
import ru.yakovlev05.test.webmessenger.dao.UserRepository;
import ru.yakovlev05.test.webmessenger.dto.message.MessageResponseDto;
import ru.yakovlev05.test.webmessenger.dto.user.UserDto;
import ru.yakovlev05.test.webmessenger.entity.MessageEntity;
import ru.yakovlev05.test.webmessenger.entity.UserEntity;
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
        var messageEntity = toMessageEntity(
                message,
                userRepository.findByUsername(senderUsername)
                        .orElseThrow(() -> new CustomException(String.format("User with username %s not found", senderUsername)))
        );

        messageRepository.save(messageEntity);

        return toMessageResponseDto(messageEntity);
    }

    @Override
    public List<MessageResponseDto> getMessages(int page, int size, long dateInMs) {
        return messageRepository.findAll()
                .stream()
                .filter(x -> x.getPublished().compareTo(new Date(dateInMs)) <= 0)
                .sorted((x1, x2) -> x2.getPublished().compareTo(x1.getPublished()))
                .skip((long) (page - 1) * size)
                .limit(size)
                .map(this::toMessageResponseDto)
                .toList();
    }

    private MessageEntity toMessageEntity(String message, UserEntity sender) {
        return MessageEntity.builder()
                .message(message)
                .published(new Date())
                .sender(sender)
                .build();
    }

    private MessageResponseDto toMessageResponseDto(MessageEntity message) {
        return MessageResponseDto.builder()
                .id(message.getId())
                .message(message.getMessage())
                .published(message.getPublished())
                .sender(toUserDto(message.getSender()))
                .build();
    }

    private UserDto toUserDto(UserEntity user) {
        return UserDto.builder()
                .name(user.getName())
                .surname(user.getSurname())
                .username(user.getUsername())
                .email(user.getEmail())
                .build();
    }
}
