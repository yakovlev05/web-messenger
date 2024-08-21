package ru.yakovlev05.test.webmessenger.service.auth;

import io.jsonwebtoken.Claims;

import java.util.Date;
import java.util.Map;
import java.util.function.Function;

public interface JwtService {
    <T> T extractClaim(String token, Function<Claims, T> claimsResolver);

    String extractUsername(String token);

    Date extractExpiration(String token);

    boolean isTokenExpired(String token);

    boolean validateToken(String token);

    String generateToken(String username);

    String generateToken(String username, Map<String, Object> claims);

    String generateRefreshToken(String username);
}
