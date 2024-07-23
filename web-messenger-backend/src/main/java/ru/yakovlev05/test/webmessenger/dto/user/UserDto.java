package ru.yakovlev05.test.webmessenger.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserDto {
    private String name;
    private String surname;
    private String username;
    private String email;
}