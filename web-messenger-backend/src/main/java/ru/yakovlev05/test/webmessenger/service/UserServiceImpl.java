package ru.yakovlev05.test.webmessenger.service;

import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import ru.yakovlev05.test.webmessenger.dao.UserRepository;
import ru.yakovlev05.test.webmessenger.entity.UserEntity;

@AllArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public UserEntity getUser(String username) {
        return userRepository.findUserEntityByUsername(username).orElse(null);
    }

    @Override
    public void deleteUser(String username) {
        userRepository.deleteUserEntitiesByUsername(username);
    }

    @Override
    public UserEntity getCurrentUser() {
        var username = SecurityContextHolder.getContext().getAuthentication().getName();
        return getUser(username);
    }

    @Override
    public UserDetailsService userDetailsService() {
        return this::getUser;
    }
}
