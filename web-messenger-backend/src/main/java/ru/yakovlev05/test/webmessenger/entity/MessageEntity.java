package ru.yakovlev05.test.webmessenger.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "messages")
public class MessageEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "sender_id") // столбец sender_id в таблице messages ссылается на столбец id в таблице users
    private UserEntity sender;

    @Column(name = "message", length = 1000)
    private String message;

    @Column(name = "published")
    private Date published;
}
