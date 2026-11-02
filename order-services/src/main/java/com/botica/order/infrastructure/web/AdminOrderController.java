package com.botica.order.infrastructure.web;

import com.botica.order.application.port.in.ListAllOrdersUseCase;
import com.botica.order.infrastructure.web.dto.OrderResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/admin/orders")
@PreAuthorize("hasRole('ADMIN')")
public class AdminOrderController {

    private final ListAllOrdersUseCase listAllOrdersUseCase;

    public AdminOrderController(ListAllOrdersUseCase listAllOrdersUseCase) {
        this.listAllOrdersUseCase = listAllOrdersUseCase;
    }

    @GetMapping
    public List<OrderResponse> listAll() {
        return listAllOrdersUseCase.listAllOrder().stream()
                .map(OrderResponse::from)
                .toList();
    }
}