package ru.yakovlev05.test.webmessenger.dto.auth;

import lombok.Data;

@Data
public class RefreshTokenRequestDto {
    private String refreshToken;
}
