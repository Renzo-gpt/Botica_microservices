package com.botica.auth.domain.model;

public class User {
    private final Long id;
    private final String email;
    private final String password;
    private final Role role;
    //private final LocalDateTime createdAt;

    public User(Long id, String email, String password, Role role) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.role = role;
    //    this.createdAt = createdAt;
    }

    public static User create(String email, String passwordHash) {
        return new User(null, email, passwordHash, Role.VENDEDOR);
    }

    public Long getId() { return id; }
    public String getEmail() { return email; }
    public String getPassword() { return password; }
    public Role getRole() { return role; }
    //public LocalDateTime getCreatedAt() { return createdAt; }
}