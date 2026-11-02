package com.botica.order.infrastructure.web.dto;

import com.botica.order.domain.model.PaymentType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record CreateOrderRequest(
        Long customerId,

        @NotNull(message = "El total de la orden es obligatorio")
        @Positive(message = "El total debe ser mayor a cero")
        BigDecimal total,

        @NotNull(message = "El tipo de pago es obligatorio")
        PaymentType paymentType
) {}