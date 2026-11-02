package com.botica.auth.infrastructure.web;

import com.botica.auth.application.port.in.LoginUseCase;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final LoginUseCase loginUseCase;

    public AuthController(LoginUseCase loginUseCase) {
        this.loginUseCase = loginUseCase;
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@Valid @RequestBody LoginRequest request) {
        String token = loginUseCase.login(request.email(), request.password());
        return ResponseEntity.ok(Map.of("token", token));
    }

    public record LoginRequest(
            @NotBlank @Email String email,
            @NotBlank String password
    ) {}
}