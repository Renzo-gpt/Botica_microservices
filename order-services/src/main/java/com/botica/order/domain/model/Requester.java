package com.botica.order.domain.model;

/**
 * Representa la identidad y permisos de quien ejecuta un caso de uso
 * desde la perspectiva del dominio (completamente desacoplado de Spring Security).
 */
public record Requester(Long userId, boolean admin) {
}