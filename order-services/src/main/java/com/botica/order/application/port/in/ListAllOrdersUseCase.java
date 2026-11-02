package com.botica.order.application.port.in;

import com.botica.order.domain.model.Order;

import java.util.List;

public interface ListAllOrdersUseCase {
        List<Order> listAllOrder();
}