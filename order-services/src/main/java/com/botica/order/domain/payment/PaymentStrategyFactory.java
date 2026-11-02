package com.botica.order.domain.payment;

import com.botica.order.domain.model.PaymentType;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class PaymentStrategyFactory {

    private final Map<String, PaymentStrategy> strategies;

    public PaymentStrategyFactory(List<PaymentStrategy> strategies) {
        this.strategies = strategies.stream()
                .collect(Collectors.toMap(PaymentStrategy::type, Function.identity()));
    }

    public PaymentStrategy getStrategy(PaymentType paymentType) {
        if (paymentType == null) {
            throw new IllegalArgumentException("El tipo de pago no puede ser nulo.");
        }
        PaymentStrategy strategy = strategies.get(paymentType.name());
        if (strategy == null) {
            throw new IllegalArgumentException("Tipo de pago no soportado: " + paymentType);
        }
        return strategy;
    }
}