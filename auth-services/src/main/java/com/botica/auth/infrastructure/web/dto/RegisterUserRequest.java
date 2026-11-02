package com.botica.auth.infrastructure.web.dto;

import com.botica.auth.domain.model.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record RegisterUserRequest(
        @NotBlank @Email String email,
        @NotBlank @Size(min = 6) String password,
        Role role
) {}