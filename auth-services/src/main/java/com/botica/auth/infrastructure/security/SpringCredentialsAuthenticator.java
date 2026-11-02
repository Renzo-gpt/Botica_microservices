package com.botica.auth.infrastructure.security;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Component;

@Component
public class SpringCredentialsAuthenticator {

    private final AuthenticationManager authenticationManager;

    public SpringCredentialsAuthenticator(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    public void authenticate(String email, String password) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
    }
}