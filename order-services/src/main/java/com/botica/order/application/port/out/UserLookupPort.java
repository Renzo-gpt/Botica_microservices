package com.botica.order.application.port.out;

import com.botica.order.domain.model.UserSummary;

import java.util.Optional;

/**
 * Asks "who is this user?" without knowing where the answer comes from.
 * Empty means the user does not exist. If the answer cannot be obtained at all (service down, timeout),
 * the implementation throws ExternalServiceException: "does not exist" and "cannot tell" are different things.
 */
public interface UserLookupPort {

    Optional<UserSummary> findById(Long userId);
}
