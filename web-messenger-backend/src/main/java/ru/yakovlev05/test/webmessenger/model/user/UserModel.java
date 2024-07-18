package ru.yakovlev05.test.webmessenger.model.user;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserModel {
    private String name;
    private String surname;
    private String username;
    private String email;
}