package com.devskiller.audit.consumer;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.time.LocalDateTime;

@JsonIgnoreProperties(ignoreUnknown = true)
public record RegisteredUserEvent(String login, LocalDateTime creationTime) {

}
