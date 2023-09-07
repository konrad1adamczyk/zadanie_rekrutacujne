package com.devskiller.audit.consumer;

import com.devskiller.audit.service.RegisteredUserEventProcessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class RegisteredUserEventReceiver {

    private final ObjectMapper mapper = new ObjectMapper();

    public RegisteredUserEventReceiver() {
        mapper.registerModule(new JavaTimeModule());
    }

    @Autowired
    private RegisteredUserEventProcessor processor;

    @KafkaListener(topics = "users", groupId = "audit")
    public void receive(ConsumerRecord<?, ?> consumerRecord) throws IOException {
        RegisteredUserEvent event = mapper.readValue(consumerRecord.value().toString(), RegisteredUserEvent.class);
        processor.processEvent(event);
    }

}
