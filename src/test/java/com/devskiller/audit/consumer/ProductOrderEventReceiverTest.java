package com.devskiller.audit.consumer;

import com.devskiller.audit.service.ProductOrderEventProcessor;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;

import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class ProductOrderEventReceiverTest {

    @Mock private ProductOrderEventProcessor processor;
    @Mock private ConsumerRecord consumerRecord;

    @InjectMocks private ProductOrderEventReceiver receiver;

    @Test
    public void invokesProcessorWithTransformedEvent() throws IOException {
        given(consumerRecord.value()).willReturn(productOrderEvent());

        receiver.receive(consumerRecord);

        verify(processor).processEvent(isA(ProductOrderEvent.class));
    }

    private String productOrderEvent() {
        return "{" +
                "\"userLogin\":\"someLogin\"," +
                "\"productId\":\"someId\"," +
                "\"creationTime\":\"2018-06-20T22:51:20.030241\"" +
                "}";
    }

}