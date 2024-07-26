package ru.yakovlev05.test.webmessenger.dto.message;

import ru.yakovlev05.test.webmessenger.dto.user.UserDto;

import java.util.Date;

public class MessageResponseDto {
    private Long id;
    private String message;
    private Date published;
    private UserDto sender;
}