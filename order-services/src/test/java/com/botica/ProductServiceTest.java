package com.botica;

import com.botica.botica.api.botica.application.port.out.ProductRepositoryPort;
import com.botica.botica.api.botica.application.service.ProductService;
import com.botica.botica.api.botica.domain.exception.ProductInactiveException;
import com.botica.botica.api.botica.domain.exception.ProductNotFoundException;
import com.botica.botica.api.botica.domain.model.Product;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

    @Mock
    ProductRepositoryPort repository;

    @InjectMocks
    ProductService productService;

    @Test
    void shouldThrowWhenProductNotExists() {
        when(repository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(ProductNotFoundException.class,
                () -> productService.findById(99L));
    }

    @Test
    void shouldThrowWhenProductIsInactive() {
        Long productId = 10L;
        Product inactiveProduct = new Product();
        inactiveProduct.setStatus(false);

        when(repository.findById(productId)).thenReturn(Optional.of(inactiveProduct));

        assertThrows(ProductInactiveException.class,
                () -> productService.findById(productId));
    }

    @Test
    void shouldReturnProductIfExistsAndIsActive() {
        Long productId = 10L;
        Product activeProduct = new Product();
        activeProduct.setName("Paracetamol");
        activeProduct.setStatus(true);

        when(repository.findById(productId)).thenReturn(Optional.of(activeProduct));

        Product valueReturned = productService.findById(productId);

        assertNotNull(valueReturned);
        assertEquals("Paracetamol", valueReturned.getName());

        verify(repository).findById(eq(productId));
        verify(repository, never()).save(any(Product.class));
    }

}