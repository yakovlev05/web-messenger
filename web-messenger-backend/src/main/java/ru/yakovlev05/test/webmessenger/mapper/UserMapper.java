package ru.yakovlev05.test.webmessenger.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import ru.yakovlev05.test.webmessenger.dto.auth.RegistrationRequestDto;
import ru.yakovlev05.test.webmessenger.dto.user.UserDto;
import ru.yakovlev05.test.webmessenger.entity.UserEntity;
import ru.yakovlev05.test.webmessenger.entity.enums.Role;

import java.util.Set;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserMapper {
    UserDto toUserDto(UserEntity user);

    @Mapping(target = "messages", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "password", source = "encodedPassword")
    UserEntity toUserEntity(RegistrationRequestDto request, String encodedPassword, Set<Role> roles);
}
