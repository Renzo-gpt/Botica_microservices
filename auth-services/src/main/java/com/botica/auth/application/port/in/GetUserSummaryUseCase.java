package com.botica.auth.application.port.in;

import com.botica.auth.domain.model.UserSummary;

import java.util.List;

public interface GetUserSummaryUseCase {

    /** @throws com.botica.auth.domain.exception.UserNotFoundException if there is no such user */
    UserSummary getSummary(Long id);
}
