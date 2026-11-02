package com.botica.auth.application.port.out;

import com.botica.auth.domain.model.User;

public interface TokenIssuerPort {
    String issueToken(User user);
}