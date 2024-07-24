package ru.yakovlev05.test.webmessenger.service.auth;

import ru.yakovlev05.test.webmessenger.dto.auth.JwtAuthResponseDto;
import ru.yakovlev05.test.webmessenger.dto.auth.LoginRequestDto;
import ru.yakovlev05.test.webmessenger.dto.auth.RegistrationRequestDto;

public interface AuthService {
    JwtAuthResponseDto registration(RegistrationRequestDto registrationRequestDto);

    JwtAuthResponseDto login(LoginRequestDto loginRequestDto);
}
