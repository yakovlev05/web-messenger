package ru.yakovlev05.test.webmessenger.dto.auth;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class JwtAuthResponseDto {
    private String token;
}
