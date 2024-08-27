package ru.yakovlev05.test.webmessenger.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.yakovlev05.test.webmessenger.entity.MessageEntity;

import java.util.Date;

public interface MessageRepository extends JpaRepository<MessageEntity, Long> {
    Page<MessageEntity> findByPublishedBefore(Date published, Pageable pageable);
}