package ru.yakovlev05.test.webmessenger.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import ru.yakovlev05.test.webmessenger.entity.UserEntity;

public interface UserService {
    UserEntity getUser(String username);

    void deleteUser(String username);

    UserEntity getCurrentUser();

    UserDetailsService userDetailsServer();
}
