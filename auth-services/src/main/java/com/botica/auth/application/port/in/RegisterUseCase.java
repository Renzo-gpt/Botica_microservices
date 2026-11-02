package com.botica.auth.application.port.in;

import com.botica.auth.domain.model.Role;
import com.botica.auth.domain.model.User;

public interface RegisterUseCase {
    User register(String email, String rawPassword, Role role);
}