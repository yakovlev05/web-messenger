package ru.yakovlev05.test.webmessenger.mapper;

import org.springframework.stereotype.Component;
import ru.yakovlev05.test.webmessenger.dto.user.UserDto;
import ru.yakovlev05.test.webmessenger.entity.UserEntity;

@Component
public class UserMapper {
    public UserDto toUserDto(UserEntity userEntity) {
        return UserDto.builder()
                .name(userEntity.getName())
                .surname(userEntity.getSurname())
                .username(userEntity.getUsername())
                .email(userEntity.getEmail())
                .build();
    }
}
