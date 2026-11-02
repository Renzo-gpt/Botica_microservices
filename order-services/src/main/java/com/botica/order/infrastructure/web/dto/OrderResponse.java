package com.botica.order.infrastructure.web.dto;

import com.botica.order.domain.model.Order;
import com.botica.order.domain.model.PaymentType;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record OrderResponse(
        Long id,
        Long customerId,
        BigDecimal total,
        PaymentType paymentType,
        LocalDateTime date
) {
    public static OrderResponse from(Order order) {
        if (order == null) {
            return null;
        }

        return new OrderResponse(
                order.getId(),
                order.getCustomerId(),
                order.getTotal(),
                order.getPaymentType(),
                order.getDate()
        );
    }
}