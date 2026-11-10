package com.botica.auth.infrastructure.web;

import com.botica.auth.application.port.in.LoginUseCase;
import com.botica.auth.application.port.in.RegisterUseCase;
import com.botica.auth.infrastructure.security.JwtTokenIssuer;
import com.botica.auth.infrastructure.web.dto.AuthResponse;
import com.botica.auth.infrastructure.web.dto.LoginRequest;
import com.botica.auth.infrastructure.web.dto.RegisterRequest;
import io.jsonwebtoken.Claims;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final RegisterUseCase registerUser;
    private final LoginUseCase loginUseCase;
    private final JwtTokenIssuer jwtTokenIssuer;

    public AuthController(RegisterUseCase registerUser, LoginUseCase loginUseCase, JwtTokenIssuer jwtTokenIssuer) {
        this.registerUser = registerUser;
        this.loginUseCase = loginUseCase;
        this.jwtTokenIssuer = jwtTokenIssuer;
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public AuthResponse register(@Valid @RequestBody RegisterRequest request) {
        String token = registerUser.register(request.email(), request.password());

        Claims claims = jwtTokenIssuer.parseClaims(token);

        return new AuthResponse(token,
                claims.get("uid", Long.class),
                claims.get("role", String.class)
        );
    }

    @PostMapping("/login")
    public AuthResponse login(@Valid @RequestBody LoginRequest request) {
        String token = loginUseCase.login(request.email(), request.password());

        Claims claims = jwtTokenIssuer.parseClaims(token);

        return new AuthResponse(
                token,
                claims.get("uid", Long.class),
                claims.get("role", String.class)
        );
    }

}