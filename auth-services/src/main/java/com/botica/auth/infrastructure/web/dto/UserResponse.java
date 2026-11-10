package com.botica.auth.infrastructure.web.dto;

import com.botica.auth.domain.model.Role;
import com.botica.auth.domain.model.User;

public record UserResponse(Long id, String email, Role role) {

    public static UserResponse from(User user) {
        return new UserResponse(user.getId(), user.getEmail(), user.getRole());
    }
}