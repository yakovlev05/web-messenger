package ru.yakovlev05.test.webmessenger.service;

import ru.yakovlev05.test.webmessenger.entity.UserEntity;

public interface UserService {
    UserEntity getUser(String username);
    void deleteUser(String username);
    UserEntity getCurrentUser();
}
