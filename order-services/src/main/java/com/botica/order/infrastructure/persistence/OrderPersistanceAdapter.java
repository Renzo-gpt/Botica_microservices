package com.botica.order.infrastructure.persistence;

import com.botica.order.application.port.out.OrderRepositoryPort;
import com.botica.order.domain.model.Order;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class OrderPersistanceAdapter implements OrderRepositoryPort {

    private final OrderJpaRepository orderJpaRepository;

    public OrderPersistanceAdapter(OrderJpaRepository orderJpaRepository) {
        this.orderJpaRepository = orderJpaRepository;
    }

    @Override
    public Order save(Order order) {
        OrderEntity entityToSave = OrderPersistanceMapper.toOrderEntity(order);
        OrderEntity savedEntity = this.orderJpaRepository.save(entityToSave);
        return OrderPersistanceMapper.toOrder(savedEntity);
    }

    @Override
    public List<Order> findAll() {
        return this.orderJpaRepository.findAll().stream()
                .map(OrderPersistanceMapper::toOrder)
                .toList();
    }

    @Override
    public Optional<Order> findById(Long id) {
        return this.orderJpaRepository.findById(id)
                .map(OrderPersistanceMapper::toOrder);
    }

    @Override
    public List<Order> findByCustomerId(Long customerId) {
        return this.orderJpaRepository.findByCustomerId(customerId).stream()
                .map(OrderPersistanceMapper::toOrder)
                .toList();
    }
}