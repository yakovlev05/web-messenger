package ru.yakovlev05.test.webmessenger.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.yakovlev05.test.webmessenger.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
}
