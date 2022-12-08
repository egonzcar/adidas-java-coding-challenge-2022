package com.adidas.backend.emailservice.infrastructure.queue;

import com.adidas.backend.emailservice.usecase.SendEmail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class MessageConsumer {

    private static final Logger log = LoggerFactory.getLogger(MessageConsumer.class);

    private final SendEmail sendEmail;

    public MessageConsumer(final SendEmail sendEmail) {
        this.sendEmail = sendEmail;
    }

    @KafkaListener(
            topics = "${spring.kafka.topic.name}",
            groupId = "${spring.kafka.consumer.group-id}"
    )
    public void consume(final String event) {
        log.info(String.format("New event received: %s", event));
        sendEmail.execute(new EmailEvent(event));
    }

}