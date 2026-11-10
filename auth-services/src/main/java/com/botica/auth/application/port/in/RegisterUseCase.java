package com.botica.auth.application.port.in;

public interface RegisterUseCase {
    String register(String email, String rawPassword);
}