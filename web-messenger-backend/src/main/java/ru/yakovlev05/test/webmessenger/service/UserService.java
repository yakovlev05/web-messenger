package ru.yakovlev05.test.webmessenger.service;

import ru.yakovlev05.test.webmessenger.entity.UserEntity;

import java.util.List;

public interface UserService {
    List<UserEntity> getAllUsers();

    void saveUser(UserEntity user);

    UserEntity getUser(long id);

    void deleteUser(long id);
}
