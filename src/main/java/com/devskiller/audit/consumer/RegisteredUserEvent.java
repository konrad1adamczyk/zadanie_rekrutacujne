package com.devskiller.audit.consumer;

import java.time.LocalDateTime;

public record RegisteredUserEvent(String login, LocalDateTime creationTime) {

}
