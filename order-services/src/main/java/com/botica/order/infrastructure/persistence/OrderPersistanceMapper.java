package com.botica.order.infrastructure.persistence;

import com.botica.order.domain.model.Order;

public class OrderPersistanceMapper {

    private OrderPersistanceMapper() {}

    public static OrderEntity toOrderEntity(Order domain) {
        if (domain == null) return null;

        OrderEntity entity = new OrderEntity();
        entity.setId(domain.getId());
        entity.setCustomerId(domain.getCustomerId());
        entity.setDate(domain.getDate());
        entity.setTotal(domain.getTotal());
        entity.setPaymentType(domain.getPaymentType());

        return entity;
    }

    public static Order toOrder(OrderEntity entity) {
        if (entity == null) return null;

        return new Order(
                entity.getId(),
                entity.getCustomerId(),
                entity.getDate(),
                entity.getTotal(),
                entity.getPaymentType()
        );
    }
}