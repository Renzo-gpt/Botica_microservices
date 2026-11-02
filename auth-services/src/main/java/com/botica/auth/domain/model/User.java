package com.botica.auth.domain.model;

public class User {
    private final Long id;
    private final String email;
    private final String passwordHash;
    private final Role role;

    public User(Long id, String email, String passwordHash, Role role) {
        this.id = id;
        this.email = email;
        this.passwordHash = passwordHash;
        this.role = role;
    }

    public static User create(String email, String passwordHash, Role role) {
        return new User(null, email, passwordHash, role != null ? role : Role.VENDEDOR);
    }

    public Long getId() { return id; }
    public String getEmail() { return email; }
    public String getPasswordHash() { return passwordHash; }
    public Role getRole() { return role; }
}