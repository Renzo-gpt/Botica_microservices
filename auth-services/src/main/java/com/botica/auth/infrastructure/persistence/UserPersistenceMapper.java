package com.botica.auth.infrastructure.persistence;

import com.botica.auth.domain.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserPersistenceMapper {

    public User toDomain(UserEntity entity) {
        if (entity == null) return null;
        return new User(entity.getId(), entity.getEmail(), entity.getPasswordHash(), entity.getRole());
    }

    public UserEntity toEntity(User domain) {
        if (domain == null) return null;
        return new UserEntity(domain.getId(), domain.getEmail(), domain.getPasswordHash(), domain.getRole());
    }
}