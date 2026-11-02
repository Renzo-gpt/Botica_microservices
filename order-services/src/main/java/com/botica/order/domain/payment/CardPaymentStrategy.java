package com.botica.order.domain.payment;

import com.botica.order.domain.model.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class CardPaymentStrategy implements PaymentStrategy {

    private static final Logger log = LoggerFactory.getLogger(CardPaymentStrategy.class);

    @Override
    public String type() {
        return "TARJETA";
    }

    @Override
    public void processPayment(Order order) {
        log.info("Procesando pago con tarjeta para la orden #{} por un monto de ${}", order.getId(), order.getTotal());
    }
}