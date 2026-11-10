package com.botica.common.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Optional;

@Component
public class JwtParser {

    private final SecretKey secretKey;

    public JwtParser(@Value("${app.jwt.secret}") String secret) {
        this.secretKey = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
    }

    /** Empty when the token is malformed, tampered, expired or lacks the expected claims. */
    public Optional<JwtPrincipal> parse(String token) {
        try {
            Claims claims = Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload();
            Long userId = claims.get("uid", Long.class);
            String role = claims.get("role", String.class);
            if (userId == null || role == null || claims.getSubject() == null) {
                return Optional.empty();
            }
            return Optional.of(new JwtPrincipal(userId, claims.getSubject(), role));
        } catch (JwtException | IllegalArgumentException e) {
            return Optional.empty();
        }
    }
}