package com.botica.auth.application.service;

import com.botica.auth.application.port.in.LoginUseCase;
import com.botica.auth.application.port.in.RegisterUseCase;
import com.botica.auth.application.port.out.CredentialsAuthenticatorPort;
import com.botica.auth.application.port.out.PasswordHasherPort;
import com.botica.auth.application.port.out.TokenIssuerPort;
import com.botica.auth.application.port.out.UserRepositoryPort;
import com.botica.auth.domain.exception.EmailAlreadyUsedException;
import com.botica.auth.domain.exception.InvalidCredentialsException;
import com.botica.auth.domain.model.AuthenticatedUser;
import com.botica.auth.domain.model.Role;
import com.botica.auth.domain.model.User;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
public class AuthService implements RegisterUseCase, LoginUseCase {

    private final UserRepositoryPort userRepository;
    private final PasswordHasherPort passwordHasher;
    private final TokenIssuerPort tokenIssuer;
    private final CredentialsAuthenticatorPort authenticator;

    public AuthService(UserRepositoryPort userRepository,
                       PasswordHasherPort passwordHasher,
                       TokenIssuerPort tokenIssuer,
                       CredentialsAuthenticatorPort authenticator) {
        this.userRepository = userRepository;
        this.passwordHasher = passwordHasher;
        this.tokenIssuer = tokenIssuer;
        this.authenticator = authenticator;
    }

    @Override
    @Transactional
    public String register(String email, String rawPassword) {
        String canonicalEmail = canonical(email);
        if (userRepository.existsByEmail(canonicalEmail)) {
            throw new EmailAlreadyUsedException(canonicalEmail);
        }
        User saved = userRepository.save(User.create(canonicalEmail, passwordHasher.hash(rawPassword)));
        return tokenIssuer.issueToken(saved.getId(), saved.getEmail(), saved.getRole());
    }

    @Override
    public String login(String email, String rawPassword) {
        String canonicalEmail = canonical(email);
        AuthenticatedUser user = authenticator.authenticate(canonicalEmail, rawPassword);
        return tokenIssuer.issueToken(user.id(), canonicalEmail, user.role());
    }


    private static String canonical(String email){
        return email.trim().toLowerCase(Locale.ROOT);
    }
}


















