package com.botica.auth.application.service;

import com.botica.auth.application.port.in.LoginUseCase;
import com.botica.auth.application.port.out.PasswordHasherPort;
import com.botica.auth.application.port.out.TokenIssuerPort;
import com.botica.auth.application.port.out.UserRepositoryPort;
import com.botica.auth.domain.exception.InvalidCredentialsException;
import com.botica.auth.domain.model.User;
import org.springframework.stereotype.Service;

@Service
public class AuthService implements LoginUseCase {

    private final UserRepositoryPort userRepository;
    private final PasswordHasherPort passwordHasher;
    private final TokenIssuerPort tokenIssuer;

    public AuthService(UserRepositoryPort userRepository,
                       PasswordHasherPort passwordHasher,
                       TokenIssuerPort tokenIssuer) {
        this.userRepository = userRepository;
        this.passwordHasher = passwordHasher;
        this.tokenIssuer = tokenIssuer;
    }

    @Override
    public String login(String email, String password) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new InvalidCredentialsException("Credenciales inválidas"));

        if (!passwordHasher.matches(password, user.getPasswordHash())) {
            throw new InvalidCredentialsException("Credenciales inválidas");
        }

        return tokenIssuer.issueToken(user);
    }
}