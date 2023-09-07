package com.devskiller.audit.consumer;

import com.devskiller.audit.service.RegisteredUserEventProcessor;
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
public class RegisteredUserEventReceiverTest {

    @Mock private RegisteredUserEventProcessor processor;
    @Mock private ConsumerRecord consumerRecord;

    @InjectMocks private RegisteredUserEventReceiver receiver;

    @Test
    public void invokesProcessorWithTransformedEvent() throws IOException {
        given(consumerRecord.value()).willReturn(registeredUserEvent());

        receiver.receive(consumerRecord);

        verify(processor).processEvent(isA(RegisteredUserEvent.class));
    }

    private String registeredUserEvent() {
        return "{" +
                "\"login\":\"someLogin\"," +
                "\"creationTime\":\"2018-06-19T23:56:25.060501\"" +
                "}";
    }
}