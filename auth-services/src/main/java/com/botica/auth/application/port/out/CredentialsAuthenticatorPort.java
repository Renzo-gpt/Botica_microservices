package com.botica.auth.application.port.out;

import com.botica.auth.domain.model.AuthenticatedUser;

public interface CredentialsAuthenticatorPort {

    /**
     * @throws com.botica.auth.domain.exception.InvalidCredentialsException if the credentials are wrong
     */
    AuthenticatedUser authenticate(String email, String rawPassword);
}
