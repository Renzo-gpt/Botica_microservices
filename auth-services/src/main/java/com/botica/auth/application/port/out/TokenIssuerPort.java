package com.botica.auth.application.port.out;

import com.botica.auth.domain.model.Role;

public interface TokenIssuerPort {

    String issueToken(Long userId, String email, Role role);
}