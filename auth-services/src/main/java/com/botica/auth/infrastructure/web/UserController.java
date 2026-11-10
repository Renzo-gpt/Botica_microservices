package com.botica.auth.infrastructure.web;

import com.botica.auth.application.port.in.GetUserSummaryUseCase;
import com.botica.auth.application.port.in.ListUsersUseCase;
import com.botica.auth.domain.model.UserSummary;
import com.botica.auth.infrastructure.web.dto.UserSummaryResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final GetUserSummaryUseCase getUserSummary;
    private final ListUsersUseCase listUsers;

    public UserController(GetUserSummaryUseCase getUserSummary, ListUsersUseCase listUsers) {
        this.getUserSummary = getUserSummary;
        this.listUsers = listUsers;
    }

    // Anyone may look up themselves; only an ADMIN may look up other users.
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or #id == authentication.principal.userId")
    public UserSummaryResponse get(@PathVariable Long id) {
        return UserSummaryResponse.from(getUserSummary.getSummary(id));
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public List<UserSummaryResponse> listAll() {
        return listUsers.listUsers()
                .stream()
                .map(UserSummary::from)
                .map(UserSummaryResponse::from)
                .toList();
    }
}