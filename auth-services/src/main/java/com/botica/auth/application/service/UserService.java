package com.botica.auth.application.service;

import com.botica.auth.application.port.in.GetUserSummaryUseCase;
import com.botica.auth.application.port.in.ListUsersUseCase;
import com.botica.auth.application.port.out.UserRepositoryPort;
import com.botica.auth.domain.exception.UserNotFoundException;
import com.botica.auth.domain.model.User;
import com.botica.auth.domain.model.UserSummary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class UserService implements ListUsersUseCase, GetUserSummaryUseCase {

    private final UserRepositoryPort users;

    public UserService(UserRepositoryPort users) {
        this.users = users;
    }

    @Override
    public List<User> listUsers() {
        return users.findAll();
    }

    @Override
    public UserSummary getSummary(Long id) {
        return users.findById(id).map(UserSummary::from).orElseThrow(() -> new UserNotFoundException(id));
    }
}