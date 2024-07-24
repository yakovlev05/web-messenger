package ru.yakovlev05.test.webmessenger.service.auth;

import io.jsonwebtoken.Claims;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Date;
import java.util.function.Function;

public interface JwtService {
    <T> T extractClaim(String token, Function<Claims, T> claimsResolver);

    String extractUsername(String token);

    Date extractExpiration(String token);

    boolean isTokenExpired(String token);

    boolean validateToken(String token, UserDetails userDetails);

    String generateToken(String username, long lifeTime);
}
