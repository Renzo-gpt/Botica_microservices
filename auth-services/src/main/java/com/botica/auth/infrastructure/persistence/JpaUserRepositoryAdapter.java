package com.botica.auth.infrastructure.persistence;

import com.botica.auth.application.port.out.UserRepositoryPort;
import com.botica.auth.domain.model.User;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class JpaUserRepositoryAdapter implements UserRepositoryPort {

    private final UserJpaRepository repository;
    private final UserPersistenceMapper mapper;

    public JpaUserRepositoryAdapter(UserJpaRepository repository, UserPersistenceMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public User save(User user) {
        UserEntity entity = mapper.toEntity(user);
        return mapper.toDomain(repository.save(entity));
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return repository.findByEmail(email).map(mapper::toDomain);
    }

    @Override
    public boolean existsByEmail(String email) {
        return repository.existsByEmail(email);
    }
}