package ru.yakovlev05.test.webmessenger.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import ru.yakovlev05.test.webmessenger.dto.user.UserDto;
import ru.yakovlev05.test.webmessenger.entity.UserEntity;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserMapper {
    UserDto toUserDto(UserEntity user);
}
