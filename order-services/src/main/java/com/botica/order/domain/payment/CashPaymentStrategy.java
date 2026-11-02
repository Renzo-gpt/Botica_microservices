package com.botica.order.domain.payment;

import com.botica.order.domain.model.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class CashPaymentStrategy implements PaymentStrategy {

    private static final Logger log = LoggerFactory.getLogger(CashPaymentStrategy.class);

    @Override
    public String type() {
        return "EFECTIVO";
    }

    @Override
    public void processPayment(Order order) {
        log.info("Procesando pago en efectivo para la orden #{} por un monto de ${}", order.getId(), order.getTotal());
    }
}