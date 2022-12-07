package com.adidas.backend.prioritysaleservice.infrastructure.queue;

import org.apache.kafka.clients.admin.NewTopic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
public class MessageProducer {

    private final static Logger log = LoggerFactory.getLogger(MessageProducer.class);

    private final NewTopic topic;
    private final KafkaTemplate<String, EmailEvent> kafkaTemplate;

    public MessageProducer(final NewTopic topic, final KafkaTemplate<String, EmailEvent> kafkaTemplate) {
        this.topic = topic;
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(final EmailEvent event) {
        log.info(String.format("Email event => %s", event.toString()));

        final Message<EmailEvent> message = MessageBuilder
                .withPayload(event)
                .setHeader(KafkaHeaders.TOPIC, topic.name())
                .build();

        kafkaTemplate.send(message);
    }
}
