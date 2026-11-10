package com.botica.auth.domain.model;

/** Public view of a user: what other parts of the system may know. Never email or password. */
public record UserSummary(Long id, String email, Role role) {

    public static UserSummary from(User user) {
        return new UserSummary(user.getId(), user.getEmail(), user.getRole());
    }
}
