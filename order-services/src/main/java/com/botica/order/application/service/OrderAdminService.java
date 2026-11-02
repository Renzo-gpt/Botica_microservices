package com.botica.order.application.service;

import com.botica.order.application.port.in.ListAllOrdersUseCase;
import com.botica.order.application.port.out.OrderRepositoryPort;
import com.botica.order.domain.model.Order;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class OrderAdminService implements ListAllOrdersUseCase {

    private final OrderRepositoryPort order;

    public OrderAdminService(OrderRepositoryPort order) {
        this.order = order;
    }

    @Override
    public List<Order> listAllOrder() {
        return order.findAll();
    }


}
