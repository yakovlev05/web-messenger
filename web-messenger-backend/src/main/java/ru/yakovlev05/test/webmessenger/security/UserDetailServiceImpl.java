package ru.yakovlev05.test.webmessenger.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.yakovlev05.test.webmessenger.dao.UserRepository;
import ru.yakovlev05.test.webmessenger.entity.UserEntity;

@Service
@RequiredArgsConstructor
public class UserDetailServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    /**
     * Загрузка пользователя по имени пользователя или по почте.
     *
     * @param usernameOrEmail может быть как именем пользователем, так и почтой
     * @return возвращает UserDetailsImpl
     * @throws UsernameNotFoundException возникает, если пользователь не найден по юзернейму и почте
     */
    @Override
    public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
        UserEntity user = userRepository
                .findByUsername(usernameOrEmail)
                .orElseGet(() -> userRepository.findByEmail(usernameOrEmail)
                        .orElseThrow(() -> new UsernameNotFoundException("User not found with username or email: " + usernameOrEmail)));
        return new UserDetailsImpl(
                user.getUsername(),
                user.getPassword(),
                user.getRoles()
        );
    }
}
