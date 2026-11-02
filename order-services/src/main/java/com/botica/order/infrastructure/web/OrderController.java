package com.botica.order.infrastructure.web;

import com.botica.common.security.JwtPrincipal;
import com.botica.order.application.port.in.OrderUseCase;
import com.botica.order.domain.model.Order;
import com.botica.order.domain.model.Requester;
import com.botica.order.infrastructure.web.dto.CreateOrderRequest;
import com.botica.order.infrastructure.web.dto.OrderResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderUseCase orderUseCase;

    public OrderController(OrderUseCase orderUseCase) {
        this.orderUseCase = orderUseCase;
    }

    @GetMapping
    public List<OrderResponse> list(@AuthenticationPrincipal JwtPrincipal principal) {
        return orderUseCase.listMine(requester(principal)).stream()
                .map(OrderResponse::from)
                .toList();
    }

    @GetMapping("/{id}")
    public OrderResponse get(@PathVariable Long id, @AuthenticationPrincipal JwtPrincipal principal) {
        return OrderResponse.from(orderUseCase.getById(id, requester(principal)));
    }

    @PostMapping
    public ResponseEntity<OrderResponse> create(@Valid @RequestBody CreateOrderRequest request,
                                                @AuthenticationPrincipal JwtPrincipal principal) {
        Order created = orderUseCase.create(
                request.customerId() != null ? request.customerId() : principal.userId(),
                request.total(),
                request.paymentType(),
                requester(principal));

        OrderResponse response = OrderResponse.from(created);
        return ResponseEntity.created(URI.create("/orders/" + response.id())).body(response);
    }

    private static Requester requester(JwtPrincipal principal) {
        return new Requester(principal.userId(), principal.isAdmin());
    }
}