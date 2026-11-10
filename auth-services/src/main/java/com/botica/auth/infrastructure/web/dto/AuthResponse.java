package com.botica.auth.infrastructure.web.dto;

public record AuthResponse(String token, Long id, String role) {
}
