package com.botica.auth.infrastructure.web;

import com.botica.auth.application.port.in.ListUsersUseCase;
import com.botica.auth.application.port.out.UserRepositoryPort;
import com.botica.auth.domain.exception.UserNotFoundException;
import com.botica.auth.domain.model.User;
import com.botica.auth.infrastructure.web.dto.UserResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/users")
@PreAuthorize("hasRole('ADMIN')")
public class AdminUserController {

    private final ListUsersUseCase listUsers;

    public AdminUserController(ListUsersUseCase listUsers) {
        this.listUsers = listUsers;
    }

    @GetMapping()
    public List<UserResponse> users() {
        return listUsers.listUsers().stream().map(UserResponse::from).toList();
    }
}