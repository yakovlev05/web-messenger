package ru.yakovlev05.test.webmessenger.service.user;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.yakovlev05.test.webmessenger.dao.UserRepository;
import ru.yakovlev05.test.webmessenger.dto.user.UserDto;
import ru.yakovlev05.test.webmessenger.entity.UserEntity;
import ru.yakovlev05.test.webmessenger.exception.CustomException;
import ru.yakovlev05.test.webmessenger.mapper.UserMapper;

@AllArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public UserDto getUser(String username) {
        UserEntity user = userRepository.findByUsername(username)
                .orElseThrow(() -> new CustomException(String.format("User with username (%s) not found", username)));

        return userMapper.toUserDto(user);
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

    @Override
    @Transactional
    public UserDto updateUser(UserDto userDto, String currentUsername) {
        UserEntity user = userRepository.findByUsername(currentUsername)
                .orElseThrow(() -> new CustomException(String.format("User with username (%s) not found", currentUsername)));

        if (!user.getUsername().equals(userDto.getUsername()) && userRepository.existsByUsername(userDto.getUsername())) {
            throw new CustomException(String.format("Username (%s) already exists", userDto.getUsername()));
        }

        if (!user.getEmail().equals(userDto.getEmail()) && userRepository.existsByEmail(userDto.getEmail())) {
            throw new CustomException(String.format("Email (%s) already exists", userDto.getEmail()));
        }

        user.setName(userDto.getName());
        user.setSurname(userDto.getSurname());
        user.setUsername(userDto.getUsername());
        user.setEmail(userDto.getEmail());

        userRepository.save(user);
        return userMapper.toUserDto(user);
    }
}
