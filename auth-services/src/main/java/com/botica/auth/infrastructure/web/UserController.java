package com.botica.auth.infrastructure.web;

import com.botica.auth.application.port.out.PasswordHasherPort;
import com.botica.auth.application.port.out.UserRepositoryPort;
import com.botica.auth.domain.exception.EmailAlreadyUsedException;
import com.botica.auth.domain.model.User;
import com.botica.auth.infrastructure.web.dto.RegisterUserRequest;
import com.botica.auth.infrastructure.web.dto.UserResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserRepositoryPort userRepository;
    private final PasswordHasherPort passwordHasher;

    public UserController(UserRepositoryPort userRepository, PasswordHasherPort passwordHasher) {
        this.userRepository = userRepository;
        this.passwordHasher = passwordHasher;
    }

    @PostMapping
    public ResponseEntity<UserResponse> register(@Valid @RequestBody RegisterUserRequest request) {
        if (userRepository.existsByEmail(request.email())) {
            throw new EmailAlreadyUsedException("El email ya se encuentra registrado");
        }

        String hashedPassword = passwordHasher.hash(request.password());
        User newUser = User.create(request.email(), hashedPassword, request.role());
        User savedUser = userRepository.save(newUser);

        return ResponseEntity.status(HttpStatus.CREATED).body(UserResponse.fromDomain(savedUser));
    }
}