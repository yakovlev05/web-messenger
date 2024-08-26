package ru.yakovlev05.test.webmessenger.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import ru.yakovlev05.test.webmessenger.dto.message.MessageResponseDto;
import ru.yakovlev05.test.webmessenger.entity.MessageEntity;
import ru.yakovlev05.test.webmessenger.entity.UserEntity;

import java.util.Date;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, imports = {Date.class})
public interface MessageMapper {
    MessageResponseDto toMessageResponseDto(MessageEntity messageEntity);

    @Mapping(target = "published", expression = "java(new Date())")
    MessageEntity toMessageEntity(String message, UserEntity sender);
}
