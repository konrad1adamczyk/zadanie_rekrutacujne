package com.devskiller.audit.model;

import java.time.LocalDateTime;

public class ProductOrder {

    private Long id;

    private String userLogin;

    private String productId;

    private LocalDateTime creationTime;

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
