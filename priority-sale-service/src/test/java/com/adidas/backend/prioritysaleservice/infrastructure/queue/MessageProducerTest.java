package com.adidas.backend.prioritysaleservice.infrastructure.queue;

import org.apache.kafka.clients.admin.NewTopic;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.Message;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class MessageProducerTest {

    @Mock
    private NewTopic topic;
    @Mock
    private KafkaTemplate<String, EmailEvent> kafkaTemplate;

    private MessageProducer testSubject;

    @BeforeEach
    void setUp() {
        testSubject = new MessageProducer(topic, kafkaTemplate);
    }

    @Test
    void should_send_email_event() {
        given(topic.name()).willReturn("email_service");

        final Throwable throwable = catchThrowable(() -> testSubject.sendMessage(new EmailEvent("user1@adiclub.com")));

        assertThat(throwable).isNull();
        verify(kafkaTemplate, times(1)).send(any(Message.class));
    }

}