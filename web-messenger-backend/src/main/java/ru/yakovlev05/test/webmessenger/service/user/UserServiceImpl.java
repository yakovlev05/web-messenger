package ru.yakovlev05.test.webmessenger.service.user;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.yakovlev05.test.webmessenger.dao.UserRepository;
import ru.yakovlev05.test.webmessenger.dto.user.UserDto;
import ru.yakovlev05.test.webmessenger.entity.UserEntity;
import ru.yakovlev05.test.webmessenger.exception.CustomException;

@AllArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public UserDto getUser(String username) {
        UserEntity user = userRepository.findByUsername(username)
                .orElseThrow(() -> new CustomException(String.format("User with username (%s) not found", username)));

        return new UserDto(
                user.getName(),
                user.getSurname(),
                user.getUsername(),
                user.getEmail()
        );
    }

    @Override
    @Transactional
    public void deleteUser(String username) {
        userRepository.deleteByUsername(username);
    }

    @Override
    public void createUser(UserEntity user) {
        if (userRepository.existsByUsername(user.getUsername()) || userRepository.existsByEmail(user.getEmail())) {
            throw new CustomException(String.format("User with username (%s) or email (%s) already exists",
                    user.getUsername(), user.getEmail()));
        }
        userRepository.save(user);
    }
}
