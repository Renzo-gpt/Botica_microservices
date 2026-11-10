package com.botica.order.infrastructure.web;

import com.botica.common.web.ErrorResponses;
import com.botica.common.web.dto.ErrorResponse;
import com.botica.order.domain.exception.ExternalServiceException;
import com.botica.order.domain.exception.InvalidOrderException;
import com.botica.order.domain.exception.OrderNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Order(Ordered.HIGHEST_PRECEDENCE)
@RestControllerAdvice
public class OrderExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(OrderExceptionHandler.class);

    @ExceptionHandler(OrderNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFound(OrderNotFoundException ex) {
        return ErrorResponses.build(HttpStatus.NOT_FOUND, ex.getMessage());
    }

    @ExceptionHandler(InvalidOrderException.class)
    public ResponseEntity<ErrorResponse> handleInvalid(InvalidOrderException ex) {
        return ErrorResponses.build(HttpStatus.BAD_REQUEST, ex.getMessage());
    }

    // The cause stays in the log (GlobalExceptionHandler's style); the client only learns that the check could not be made.
    @ExceptionHandler(ExternalServiceException.class)
    public ResponseEntity<ErrorResponse> handleExternal(ExternalServiceException ex) {
        log.warn("External dependency failed: {}", ex.getMessage(), ex.getCause());
        return ErrorResponses.build(HttpStatus.SERVICE_UNAVAILABLE, "User verification is temporarily unavailable");
    }
}