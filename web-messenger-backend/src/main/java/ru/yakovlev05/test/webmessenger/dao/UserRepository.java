package ru.yakovlev05.test.webmessenger.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.yakovlev05.test.webmessenger.entity.UserEntity;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByUsername(String username);

    Optional<UserEntity> findByEmail(String email);

    void deleteUserEntitiesByUsername(String username);

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);
}
