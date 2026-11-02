package com.botica.order.application.port.in;

import com.botica.order.domain.model.Order;
import com.botica.order.domain.model.PaymentType;
import com.botica.order.domain.model.Requester;

import java.math.BigDecimal;
import java.util.List;

public interface OrderUseCase {

    Order create(Long customerId, BigDecimal total, PaymentType paymentType, Requester requester);

    Order getById(Long id, Requester requester);

    List<Order> listMine(Requester requester);
}