package ru.yakovlev05.test.webmessenger.service.message;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.yakovlev05.test.webmessenger.dao.MessageRepository;
import ru.yakovlev05.test.webmessenger.dao.UserRepository;
import ru.yakovlev05.test.webmessenger.dto.message.MessageResponseDto;
import ru.yakovlev05.test.webmessenger.entity.MessageEntity;
import ru.yakovlev05.test.webmessenger.entity.UserEntity;
import ru.yakovlev05.test.webmessenger.exception.CustomException;
import ru.yakovlev05.test.webmessenger.mapper.MessageMapper;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService {
    private final MessageRepository messageRepository;
    private final UserRepository userRepository;
    private final MessageMapper messageMapper;

    @Override
    public MessageResponseDto save(String message, String senderUsername) {
        UserEntity sender = userRepository.findByUsername(senderUsername)
                .orElseThrow(() -> new CustomException(String.format("User with username %s not found", senderUsername)));
        MessageEntity messageEntity = messageMapper.toMessageEntity(message, sender);

        messageRepository.save(messageEntity);

        return messageMapper.toMessageResponseDto(messageEntity);
    }

    @Override
    public List<MessageResponseDto> getMessages(int page, int size, long dateInMs) {
        return messageRepository.findAll()
                .stream()
                .filter(x -> x.getPublished().compareTo(new Date(dateInMs)) <= 0)
                .sorted((x1, x2) -> x2.getPublished().compareTo(x1.getPublished()))
                .skip((long) (page - 1) * size)
                .limit(size)
                .map(messageMapper::toMessageResponseDto)
                .toList();
    }
}
