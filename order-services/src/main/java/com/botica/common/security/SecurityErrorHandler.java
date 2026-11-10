package com.botica.common.security;

import com.botica.common.web.dto.ErrorResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Component
public class SecurityErrorHandler implements AuthenticationEntryPoint, AccessDeniedHandler {

    private final ObjectMapper mapper;

    public SecurityErrorHandler(ObjectMapper mapper) {
        this.mapper = mapper;
    }

    // 401: we don't know who you are (no token, invalid or expired token)
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException ex) throws IOException {
        writeErrorResponse(response, HttpStatus.UNAUTHORIZED, "No autenticado: Debe iniciar sesión o enviar un token válido");
    }

    // 403: we know who you are, but you are not allowed
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response,
                       AccessDeniedException ex) throws IOException {
        writeErrorResponse(response, HttpStatus.FORBIDDEN,"Acceso denegado: No tiene permisos suficientes para este recurso");
    }

    private void writeErrorResponse(HttpServletResponse response,
                                    HttpStatus status, String message) throws IOException {
        response.setStatus(status.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        mapper.writeValue(response.getOutputStream(), ErrorResponse.of(status.value(), message, List.of()));
    }
}