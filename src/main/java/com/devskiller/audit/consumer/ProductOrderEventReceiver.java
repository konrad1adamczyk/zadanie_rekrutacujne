package com.devskiller.audit.consumer;

import com.devskiller.audit.service.ProductOrderEventProcessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.util.StdDateFormat;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class ProductOrderEventReceiver {

    @Autowired
    private ProductOrderEventProcessor processor;

    private ObjectMapper objectMapper = new ObjectMapper()
            .registerModule(new JavaTimeModule())
            .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
            .setDateFormat(new StdDateFormat().withColonInTimeZone(true));


    @KafkaListener(topics = "orders", groupId = "audit")
    public void receive(ConsumerRecord<?, ?> consumerRecord) throws IOException {
        String value = (String) consumerRecord.value();
        try {
            ProductOrderEvent event = objectMapper.readValue(value, ProductOrderEvent.class);
            processor.processEvent(event);
        } catch (IOException e) {
            throw new IOException("Error deserializing the Kafka message", e);
        }
    }
}