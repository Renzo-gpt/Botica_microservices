package com.botica.auth.application.port.in;

import com.botica.auth.domain.model.User;

import java.util.List;

public interface ListUsersUseCase {

    List<User> listUsers();

}
