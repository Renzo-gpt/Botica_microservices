package com.botica;

import com.botica.botica.api.botica.application.port.in.CreateCustomerCommand;
import com.botica.botica.api.botica.application.port.out.CustomerRepositoryPort;
import com.botica.botica.api.botica.application.service.CustomerService;
import com.botica.botica.api.botica.domain.exception.CustomerAlreadyExistsException;
import com.botica.botica.api.botica.domain.model.Customer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CustomerServiceTest {

	@Mock
	CustomerRepositoryPort repository;

	@InjectMocks
    CustomerService customerService;

	@Test
	void shouldSaveCustomerOnce() {
		// Arrange
		CreateCustomerCommand cmd = new CreateCustomerCommand("72819203", "Carlos", "Mendoza", "987654321", "carlos@email.com");
		Customer customer = new Customer();
		customer.setDni(cmd.getDni());
		customer.setEmail(cmd.getEmail());

		when(repository.existsByDni(cmd.getDni())).thenReturn(false);
		when(repository.existsByEmail(cmd.getEmail())).thenReturn(false);
		when(repository.save(any(Customer.class))).thenReturn(customer);

		// Act
		Customer valueReturned = customerService.create(cmd);

		// Assert
		assertNotNull(valueReturned);
		assertEquals("72819203", valueReturned.getDni());

		verify(repository, times(1)).save(any(Customer.class));

	}

	@Test
	void shouldThrowWhenDniAlreadyExists() {
		CreateCustomerCommand cmd = new CreateCustomerCommand("72819203", "Carlos", "Mendoza", "987654321", "carlos@email.com");
		when(repository.existsByDni(cmd.getDni())).thenReturn(true);

		assertThrows(CustomerAlreadyExistsException.class,
				() -> customerService.create(cmd));

		verify(repository, never()).save(any(Customer.class));
	}

	@Test
	void shouldThrowWhenEmailAlreadyExists() {
		CreateCustomerCommand cmd = new CreateCustomerCommand("72819203", "Carlos", "Mendoza", "987654321", "carlos@email.com");
		when(repository.existsByEmail(cmd.getEmail())).thenReturn(true);

		assertThrows(CustomerAlreadyExistsException.class,
				() -> customerService.create(cmd));

		verify(repository, never()).save(any(Customer.class));
	}

	@Test
	void shouldReturnAllCustomers() {

		when(repository.findAll()).thenReturn(List.of(new Customer(), new Customer()));

		List<Customer> customers = customerService.findAll();

		assertNotNull(customers);
		assertEquals(2, customers.size());
		verify(repository).findAll();
	}
}


