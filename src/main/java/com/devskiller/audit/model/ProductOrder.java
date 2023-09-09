package com.devskiller.audit.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

@Entity
public class ProductOrder {

    @Id
    private Long id;

    private String userLogin;

    private String productId;

    private LocalDateTime creationTime;

    public ProductOrder(Long id , String userLogin, String productId, LocalDateTime creationTime) {
    }

    public Long getId() {
        return id;
    }

    public String getUserLogin() {
        return userLogin;
    }

    public String getProductId() {
        return productId;
    }

    public LocalDateTime getCreationTime() {
        return creationTime;
    }
}
