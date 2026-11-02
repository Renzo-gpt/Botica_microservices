package com.botica.order.infrastructure.web;

import com.botica.common.web.ErrorResponses;
import com.botica.common.web.dto.ErrorResponse;
import com.botica.order.domain.exception.InvalidOrderException;
import com.botica.order.domain.exception.OrderNotFoundException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Order(Ordered.HIGHEST_PRECEDENCE)
@RestControllerAdvice
public class OrderExceptionHandler {

    @ExceptionHandler(OrderNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFound(OrderNotFoundException ex) {
        return ErrorResponses.build(HttpStatus.NOT_FOUND, ex.getMessage());
    }

    @ExceptionHandler(InvalidOrderException.class)
    public ResponseEntity<ErrorResponse> handleInvalid(InvalidOrderException ex) {
        return ErrorResponses.build(HttpStatus.BAD_REQUEST, ex.getMessage());
    }
}