package com.botica.auth.infrastructure.security;

import com.botica.auth.application.port.out.CredentialsAuthenticatorPort;
import com.botica.auth.domain.exception.InvalidCredentialsException;
import com.botica.auth.domain.model.AuthenticatedUser;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
public class SpringCredentialsAuthenticator implements CredentialsAuthenticatorPort {

    private final AuthenticationManager authenticationManager;

    public SpringCredentialsAuthenticator(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public AuthenticatedUser authenticate(String email, String rawPassword) {
        try {
            var auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(email, rawPassword));
            return ((SecurityUser) auth.getPrincipal()).toAuthenticatedUser();
        } catch (AuthenticationException e) {
            throw new InvalidCredentialsException();
        }
    }
}