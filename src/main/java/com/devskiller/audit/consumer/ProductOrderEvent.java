package com.devskiller.audit.consumer;

import java.time.LocalDateTime;

public record ProductOrderEvent(String userLogin, String productId, LocalDateTime creationTime) {
}
