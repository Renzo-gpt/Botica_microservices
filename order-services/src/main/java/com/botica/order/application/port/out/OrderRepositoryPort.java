package com.botica.order.application.port.out;

import com.botica.order.domain.model.Order;

import java.util.List;
import java.util.Optional;

public interface OrderRepositoryPort {
    List<Order> findAll();
    Order save(Order order);
    Optional<Order> findById(Long id);
    List<Order> findByCustomerId(Long customerId);
}
