package com.botica.order.application.service;

import com.botica.order.application.port.in.OrderUseCase;
import com.botica.order.application.port.out.OrderRepositoryPort;
import com.botica.order.domain.exception.OrderNotFoundException;
import com.botica.order.domain.model.Order;
import com.botica.order.domain.model.PaymentType;
import com.botica.order.domain.model.Requester;
import com.botica.order.domain.payment.PaymentStrategyFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
@Transactional
public class OrderService implements OrderUseCase {

    private final OrderRepositoryPort orderRepository;
    private final PaymentStrategyFactory paymentStrategyFactory;

    public OrderService(OrderRepositoryPort orderRepository, PaymentStrategyFactory paymentStrategyFactory) {
        this.orderRepository = orderRepository;
        this.paymentStrategyFactory = paymentStrategyFactory;
    }

    @Override
    public Order create(Long customerId, BigDecimal total, PaymentType paymentType, Requester requester) {
        Order order = Order.create(customerId, total, paymentType);
        Order savedOrder = orderRepository.save(order);

        // Ejecución de la estrategia de pago elegida
        paymentStrategyFactory.getStrategy(paymentType).processPayment(savedOrder);
        return savedOrder;
    }

    @Override
    @Transactional(readOnly = true)
    public Order getById(Long id, Requester requester) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new OrderNotFoundException(id));

        if (!requester.admin() && !order.getCustomerId().equals(requester.userId())) {
            throw new OrderNotFoundException(id);
        }
        return order;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Order> listMine(Requester requester) {
        return orderRepository.findByCustomerId(requester.userId());
    }

}