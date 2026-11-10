package com.botica.order.infrastructure.lookup;

/** The contract of GET /users/{id}, owned by auth-service. This is task-service's own copy: nothing is imported from the other project. */
record UserSummaryResponse(Long id, String name, String role) {
}
