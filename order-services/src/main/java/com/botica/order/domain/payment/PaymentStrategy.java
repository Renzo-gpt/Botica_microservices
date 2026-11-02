package com.botica.order.domain.payment;

import com.botica.order.domain.model.Order;

public interface PaymentStrategy {
    String type();
    void processPayment(Order order);
}