package ru.yakovlev05.test.webmessenger.service.user;

import lombok.AllArgsConstructor;
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
    public void createUser(UserEntity user) {
        userRepository.save(user);
    }
}
