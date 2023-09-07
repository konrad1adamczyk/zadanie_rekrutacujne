package com.devskiller.audit.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity(name = "USERS")
public class User {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    @Column(unique = true, nullable = false)
    private String login;

    private LocalDateTime creationTime;

    protected User() {}

    public User(String login, LocalDateTime creationTime) {
        this.login = login;
        this.creationTime = creationTime;
    }

    public Long getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public LocalDateTime getCreationTime() {
        return creationTime;
    }
}
