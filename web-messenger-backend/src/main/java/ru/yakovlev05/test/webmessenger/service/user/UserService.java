package ru.yakovlev05.test.webmessenger.service.user;

import ru.yakovlev05.test.webmessenger.dto.user.UserDto;
import ru.yakovlev05.test.webmessenger.entity.UserEntity;

public interface UserService {
    UserDto getUser(String username);

    void deleteUser(String username);

    void createUser(UserEntity user);
}
