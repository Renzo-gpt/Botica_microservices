package com.botica.auth.infrastructure.web.dto;

import com.botica.auth.domain.model.Role;
import com.botica.auth.domain.model.UserSummary;

public record UserSummaryResponse(Long id, String email, Role role)  {

    public static UserSummaryResponse from(UserSummary s) {
        return new UserSummaryResponse(s.id(), s.email(), s.role());
    }
}
