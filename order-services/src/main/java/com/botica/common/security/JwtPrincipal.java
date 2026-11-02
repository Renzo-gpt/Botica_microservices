package com.botica.common.security;

public record JwtPrincipal(Long userId, String email, String role) {

    public boolean isAdmin() {
        return "ADMIN".equals(role);
    }
}