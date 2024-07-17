package ru.yakovlev05.test.webmessenger.service;

import lombok.AllArgsConstructor;
import ru.yakovlev05.test.webmessenger.dao.UserRepository;
import ru.yakovlev05.test.webmessenger.entity.UserEntity;

import java.util.List;

@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public List<UserEntity> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public void saveUser(UserEntity user) {
        userRepository.save(user);
    }

    @Override
    public UserEntity getUser(long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteUser(long id) {
        userRepository.deleteById(id);
    }
}
