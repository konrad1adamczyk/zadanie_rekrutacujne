package com.devskiller.audit;

import com.devskiller.audit.model.User;
import com.devskiller.audit.repository.UserRepository;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.test.EmbeddedKafkaBroker;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.kafka.test.utils.KafkaTestUtils;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static java.util.concurrent.TimeUnit.SECONDS;
import static org.assertj.core.api.Assertions.assertThat;
import static org.awaitility.Awaitility.await;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@EmbeddedKafka(topics = "users")
public class RegisteredUserEventEndToEndTest {

    @Autowired private EmbeddedKafkaBroker embeddedKafkaBroker;
    @Autowired private UserRepository userRepository;
    private Producer<Integer, String> producer;

    @BeforeEach
    void setUp() {
        producer = new DefaultKafkaProducerFactory<Integer, String>(KafkaTestUtils.producerProps(embeddedKafkaBroker)).createProducer();
    }

    @AfterEach
    void tearDown() {
        producer.close();
    }

    @Test
    public void savesEventToRepository() {
        String login = "StephenKing";
        String data = registeredUserEvent(login, "2018-06-19T23:56:25.060501");
        producer.send(new ProducerRecord<>("users", data));

        await().atMost(10, SECONDS).until(() -> userRepository.count() == 1);

        User user = userRepository.findByLogin(login);
        assertThat(user.getId()).isNotNull();
        assertThat(user.getCreationTime()).isNotNull();
    }

    private String registeredUserEvent(String login, String creationTime) {
        return "{" +
                    "\"login\":\"" + login + "\"," +
                    "\"creationTime\":\"" + creationTime + "\"" +
                "}";
    }

}
