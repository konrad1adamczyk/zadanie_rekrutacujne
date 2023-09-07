package com.devskiller.audit.consumer;

import com.devskiller.audit.service.RegisteredUserEventProcessor;
import org.apache.kafka.clients.consumer.ConsumerRecord;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class ProductOrderEventReceiver {

    private RegisteredUserEventProcessor processor;

    public void receive(ConsumerRecord<?, ?> consumerRecord) throws IOException {
        throw new UnsupportedEncodingException(/*TODO*/);
    }

}
