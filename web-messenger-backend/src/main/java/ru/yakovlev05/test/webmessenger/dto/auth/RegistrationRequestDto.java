package ru.yakovlev05.test.webmessenger.dto.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegistrationRequestDto {
    private String name;
    private String surname;
    private String username;
    private String password;
    private String email;
}