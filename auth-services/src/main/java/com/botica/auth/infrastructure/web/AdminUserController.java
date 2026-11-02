package com.botica.auth.infrastructure.web;

import com.botica.auth.application.port.out.UserRepositoryPort;
import com.botica.auth.domain.exception.UserNotFoundException;
import com.botica.auth.domain.model.User;
import com.botica.auth.infrastructure.web.dto.UserResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/users")
public class AdminUserController {

    private final UserRepositoryPort userRepository;

    public AdminUserController(UserRepositoryPort userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/{email}")
    public ResponseEntity<UserResponse> getUserByEmail(@PathVariable String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("Usuario no encontrado con email: " + email));

        return ResponseEntity.ok(UserResponse.fromDomain(user));
    }
}